## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).



### Alterações na Gramática da Linguagem DL

As seguintes modificações foram feitas na gramática para adicionar novos operadores:

#### a. Operadores de Igualdade e Relacionais

Os operadores de igualdade (`==`), diferença (`!=`) e maior ou igual (`>=`) foram adicionados. Eles possuem a mesma precedência dos operadores relacionais existentes (`<`, `<=`, `>`).

**Precedência:** Operadores relacionais têm precedência maior que operadores lógicos (AND, OR) e menor que operadores aritméticos.

**Regra de Gramática (Modificada):**

```bnf
relational_op -> < | <= | > | >= | == | !=

rel -> arith (relational_op arith)*
```

(A regra arith permanece inalterada, lidando com +, -, *).
b. Operador Lógico AND
O operador lógico AND (&) foi adicionado.
Precedência: O operador & tem precedência maior que | (OR) e menor que todos os operadores relacionais.
Regras de Gramática (Novas/Modificadas):
```
logical_and_op -> &

and_expr -> rel (logical_and_op rel)*

expr -> and_expr (OR and_expr)*
```

(A regra factor e term permanecem inalteradas).
Resumo da Precedência (da maior para a menor):
Parênteses: ()
Multiplicativo: *
Aditivo: +, -
Relacionais: <, <=, >, >=, ==, !=
Lógico AND: &
Lógico OR: |
Atribuição: =


---

### Observações Finais:

*   **Geração de Código LLVM-IR:** A parte do `Emitter.java` não foi modificada neste problema, pois o foco era na análise léxica e sintática. Para gerar o LLVM-IR adequado para esses novos operadores, você precisaria estender a classe `Emitter` e adicionar métodos para visitar os nós `And` e `Rel` (para os novos operadores) e gerar as instruções LLVM correspondentes (ex: `icmp eq`, `icmp ne`, `icmp sge` para inteiros/reais, `and i1` para booleanos).
*   **Teste:** Para testar, você pode criar um arquivo `prog.dl` com expressões usando esses novos operadores, por exemplo:
    ```dl
    programa TesteNovoOperadores
    inicio
        inteiro a;
        booleano b;

        a = 10;
        b = (a == 10) & (a != 5) | (a >= 20);
        escreva(a);
        escreva(b);
    fim.
    ```
    E então, execute `DL.java`. O programa deve gerar a árvore sintática sem erros.

Com essas mudanças, o lexer e o parser da linguagem DL estarão prontos para lidar com os novos operadores de igualdade, relacionais (`==`, `!=`, `>=`) e o operador lógico AND (`&`), respeitando as precedências especificadas.