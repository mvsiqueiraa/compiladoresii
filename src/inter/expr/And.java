package inter.expr;

import lexer.Token;
import lexer.Tag;

public class And extends Logical {

    public And(Expr e1, Expr e2) {
        // Usa o token de AND e os dois operandos
        super(new Token(Tag.AND, "&"), e1, e2);
    }

    @Override
    public String toString() {
        return super.op.lexeme();
    }
}