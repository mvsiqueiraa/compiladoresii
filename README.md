# Compilador para a Linguagem Didática (DL)

Este projeto consiste em um compilador para a Linguagem Didática (DL), desenvolvido como parte da disciplina de Compiladores. O compilador implementa as fases de análise léxica, sintática e semântica, e foi estendido para incluir novos operadores relacionais e lógicos.

## Estrutura do Projeto

O projeto está organizado nos seguintes pacotes principais:

- **`src/lexer`**: Contém as classes responsáveis pela análise léxica (Lexer, Token, Tag).
- **`src/parser`**: Contém a classe do analisador sintático (Parser).
- **`src/inter`**: Contém as classes para a representação intermediária (árvore sintática), como nós de expressão (`Expr`), comandos (`Stmt`) e outros.
- **`src/dl`**: Contém a classe principal (`DL.java`) para execução do compilador.



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
