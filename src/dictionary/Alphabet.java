
package dictionary;

import java.util.Random;
/**
 *
 * @author humzarahman
 */


public enum Alphabet 
{
    A(1),
    B(3),
    C(3),
    D(2),
    E(1),
    F(4),
    G(2),
    H(4),
    I(1),
    J(8),
    K(5),
    L(1),
    M(3),
    N(1),
    O(1),
    P(3),
    Q(10),
    R(1),
    S(1),
    T(1),
    U(1),
    V(4),
    w(4),
    X(8),
    Y(4),
    Z(10);
    
    private final int score;
    

    Alphabet(int score)
    {
        this.score = score;
    }
    
    public String get()
    {
        return this.toString().toLowerCase();
    }
    
    public int getScore()
    {
        return this.score;
    }

    public static Alphabet newRandom()
    {
        Random random = new Random();
        int value;
        value = random.nextInt(26);
        Alphabet alphabet = Alphabet.values()[value];
        
        return alphabet;
    }
    
}
