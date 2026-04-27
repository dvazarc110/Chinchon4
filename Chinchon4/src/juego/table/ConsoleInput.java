package juego.table;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInput {
   
    private final Scanner keyboard;

    public ConsoleInput(Scanner keyboard) {
        this.keyboard = keyboard;
    }

   
    public ConsoleInput() {
        keyboard = new Scanner(System.in);
    }

    public void cleanInput() {
        keyboard.nextLine();
    }
    
    
    
    public int readInt() {
        boolean isValid;
        int in = 0;

        do {
            isValid = true;
            try {
                in = keyboard.nextInt();
            } catch (NumberFormatException | InputMismatchException e) {
                System.err.println("Error en el INT");
                System.out.println("Introduzca un número");
                isValid = false;
            } finally {
                cleanInput();
            }
        } while (!isValid);
        return in;

    }

   
    public int readIntLessThan(int upperBound) {
        int in;

        do {
            in = readInt();
            if (upperBound <= in) {
                System.err.printf("El INT introducido no es menor que %d\n", upperBound);
            }
        } while (upperBound <= in);

        return (in);
    }

   
    public int readIntLessOrEqualThan(int upperBound) {
        int in;

        do {
            in = readInt();
            if (upperBound < in) {
                System.err.printf("El INT introducido no es menor o igual a %d\n", upperBound);
            }
        } while (upperBound < in);

        return (in);
    }

    
    public int readIntGreaterThan(int lowerBound) {
        int in;

        do {
            in = readInt();
            if (lowerBound >= in) {
                System.err.printf("El INT introducido no es mayor que %d\n", lowerBound);
            }
        } while (lowerBound >= in);

        return (in);
    }

    
    public int readIntGreaterOrEqualThan(int lowerBound) {
        int in;

        do {
            in = readInt();
            if (lowerBound > in) {
                System.err.printf("El INT introducido no es mayor o igual que %d\n", lowerBound);
            }
        } while (lowerBound > in);

        return (in);
    }

   
    public int readIntInRange(int lowerBound, int upperBound) {
        int in;

        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("\nEl upperBound es menor que el lowerBound");
        } else {
            do {
                in = readInt();
                if (upperBound < in || lowerBound > in) {
                    System.err.printf("El INT introducido no esta entre los valores %d y %d\n", lowerBound, upperBound);
                    System.out.printf("Introduzca otro número que entre dentro del rango %d - %d :\n", lowerBound, upperBound);
                }
            } while ((upperBound < in || lowerBound > in));
        }

        return (in);
    }

 
    
    public double readDouble() {
        boolean isValid;
        double doubl = 0;

        do {
            isValid = true;
            try {
                doubl = keyboard.nextDouble();
            } catch (NumberFormatException | InputMismatchException e) {
                System.err.println("Error en el DOUBLE");
                isValid = false;
            } finally {
                cleanInput();
            }
        } while (!isValid);
        return doubl;

    }

   
    public double readDoubleLessThan(double upperBound) {
        double doubl;

        do {
            doubl = readDouble();
            if (upperBound <= doubl) {
                System.err.printf("El DOUBLE introducido no es menor que %f\n", upperBound);
            }
        } while (upperBound <= doubl);

        return (doubl);
    }

   
    public double readDoubleLessOrEqualThan(double upperBound) {
        double doubl;

        do {
            doubl = readDouble();
            if (upperBound < doubl) {
                System.err.printf("El DOUBLE introducido no es menor o igual a %f\n", upperBound);
            }
        } while (upperBound < doubl);

        return (doubl);
    }

    
    public double readDoubleGreaterThan(double lowerBound) {
        double doubl;

        do {
            doubl = readDouble();
            if (lowerBound >= doubl) {
                System.err.printf("El DOUBLE introducido no es mayor que %f\n", lowerBound);
            }
        } while (lowerBound >= doubl);

        return (doubl);
    }

    
    public double readDoubleGreaterOrEqualThan(double lowerBound) {
        double doubl;

        do {
            doubl = readDouble();
            if (lowerBound > doubl) {
                System.err.printf("El DOUBLE introducido no es mayor o igual que %f\n", lowerBound);
            }
        } while (lowerBound > doubl);

        return (doubl);
    }

   
    public double readDoubleInRange(double lowerBound, double upperBound) {
        double doubl;

        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("\nEl upperBound es menor que el lowerBound");
        } else {
            do {
                doubl = readDouble();
                if (upperBound < doubl || lowerBound > doubl) {
                    System.err.printf("El DOUBLE introducido no esta entre los valores %f y %f\n", lowerBound,
                            upperBound);
                }
            } while ((upperBound < doubl || lowerBound > doubl));
        }

        return (doubl);
    }

   
    
    public char readChar() {
        String str;
        char cha = ' ';
        boolean isValid;

        do {
            isValid = true;
            str = keyboard.nextLine();
            if (str.length() == 1) {
                cha = str.charAt(0);
            } else {
                System.err.println("Error en el CHAR");
                isValid = false;
            }
        } while (!isValid);

        return cha;
    }

   

    public String readString() {
        return keyboard.nextLine();
    }

    public String readString(int maxLength) {
        String str;

        if (maxLength < 0) {
            throw new IllegalArgumentException("\nmaxLength es menor que 0");
        }
        do {
            str = readString();
            if (str.length() > maxLength) {
                System.err.println("El tamaño del STRING es mayor al especificado");
            }
        } while (str.length() > maxLength);

        return str;
    }

    //Boolean
    public boolean readBooleanUsingChar(char affirmativeValue) {
        return readChar() == affirmativeValue;
    }

    public boolean readBooleanUsingChar() {
        return readBooleanUsingChar('s');
    }

    public boolean readBooleanUsingChar(char affirmativeValue, char negativeValue) {
        boolean bool = true, isValid = false;
        char cha;

        do {
            cha = readChar();
            if (cha == affirmativeValue) {
                isValid = true;

            } else if (cha == negativeValue) {
                bool = false;
                isValid = true;

            } else {
                System.err.println("El CHAR especificado no sirve, introduzca " + affirmativeValue + " para TRUE o "
                        + negativeValue + " para FALSE");
            }
        } while (!isValid);

        return bool;
    }

}