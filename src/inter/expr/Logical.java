package inter.expr;

import lexer.Token;
import lexer.Tag;
import inter.Node; // Importa Node para usar Node.error()

public abstract class Logical extends Expr {

    protected Expr expr1;
    protected Expr expr2;

    public Logical(Token tok, Expr e1, Expr e2) {
        // Chama o construtor da classe base Expr: Expr(Token op, Tag type)
        // Para operadores lógicos (OR, AND), o tipo de resultado é sempre BOOL.
        // 'tok' será o token do operador (ex: Tag.AND para '&', Tag.OR para '|')
        super(tok, Tag.BOOL);

        this.expr1 = e1;
        this.expr2 = e2;

        addChild(expr1);
        addChild(expr2);

        // Realiza a verificação de tipo:
        // Ambos os operandos de um operador lógico devem ser do tipo booleano.
        if (e1.type() != Tag.BOOL || e2.type() != Tag.BOOL) {
            Node.error("Operadores não-booleanos para o operador lógico '" + tok.lexeme() + "'");
        }
    }

    public Expr getExpr1() {
        return expr1;
    }

    public Expr getExpr2() {
        return expr2;
    }
}