package beginner_problems;

public class Patterns {
    public void pattern1(int n) {
        for (int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public void pattern2(int n) {
        for(int i=0;i<n;i++) {
            for (int j = 0;j <= i;j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public void pattern3(int n) {
        for (int i=1;i<=n;i++){
            for (int j=1;j<=i;j++){
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public void pattern4(int n) {
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.print(i);
            }
            System.out.println();
        }
    }

    public void pattern5(int n) {
        for(int i=0;i<n;i++){
            for(int j=n-1;j>=i;j--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public void pattern6(int n) {
        for(int i=n;i>=1;i--){
            for (int j=1;j<=i;j++){
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public void pattern7(int n) {
        int total = 2*n-1;
        int temp=0;
        for (int i=1;i<=n;i++) {
            for (int j=1; j <= n-i; j++) {
                System.out.print(" ");
            }

            for (int l = 1; l <= i + temp; l++) {
                System.out.print("*");
            }

            for (int k=n+i; k <= 2*n-1; k++){
                System.out.print(" ");
            }
            temp ++;
            System.out.println();
        }
    }

    public void pattern8(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }

            for (int k = 0; k < (2 * (n - i) - 1); k++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

    public void pattern9(int n) {
        for (int i = 1 ; i <= n; i++){

            for (int j = 1 ; j <= n-i; j++) {
                System.out.print(" ");
            }
            for (int k = 1 ; k <= 2*i-1; k++){
                System.out.print("*");
            }
            for (int l = n+i; l <= 2*n-1; l++) {
                System.out.print(" ");
            }

            System.out.println();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }

            for (int k = 0; k < (2 * (n - i) - 1); k++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

    public void pattern10(int n) {
        for (int i = 1; i <= 2*n-1; i++) {
            if (i > n) {
                for (int j = 1 ; j <= 2*n - i; j++){
                    System.out.print("*");
                }
            } else {
                for (int j = 1; j <= i;j++){
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }

    public void pattern11(int n) {
        for (int i = 1; i <= n; i++) {
            if (i % 2 != 0) {
                boolean one = true;
                for (int j = 1 ; j <= i ; j++) {
                    if (one){
                        System.out.print("1 ");
                        one=false;
                    } else {
                        System.out.print("0 ");
                        one=true;
                    }
                }
            } else {
                boolean zero = true;
                for (int j = 1 ; j <= i ; j++) {
                    if (zero) {
                        System.out.print("0 ");
                        zero = false;
                    } else {
                        System.out.print("1 ");
                        zero=true;
                    }
                }
            }
            System.out.println();
        }
    }

    public void pattern12(int n) {
        for (int i = 1 ; i <= n ; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            for (int j = 1 ; j <= 2*n - i*2 ; j++) {
                System.out.print(" ");
            }
            for (int j = i; j >= 1 ; j--) {
                System.out.print(j);
            }

            System.out.println();
        }
    }

    public void pattern13(int n) {
        int cnt = 0;
        for (int i=1; i <= n ; i++){
            for (int j = 1; j <= i; j++){
                cnt = cnt + 1;
                System.out.print(cnt + " ");
            }
            System.out.println();
        }
    }

    public void pattern14(int n) {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i=0;i<n;i++){
            for (int j=0;j<=i;j++){
                System.out.print(str.charAt(j));
            }
            System.out.println();
        }
    }

    public void pattern15(int n) {
        for (int i=n;i>=1;i--){
            int cnt = 65;
            for (int j=1;j<=i;j++){
                System.out.print((char)cnt);
                cnt = cnt + 1;
            }
            System.out.println();
        }
    }

    public void pattern16(int n) {
        int cnt = 65;
        for (int i = 0 ; i< n; i++) {
            for (int j= 0; j<=i;j++) {
                System.out.print((char) cnt);
            }
            cnt = cnt + 1;
            System.out.println();
        }
    }

    public void pattern17(int n) {
        for (int i = 1; i <= n; i++) {
            char x = 'A';
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print(x);
                x++;
            }
            x -= 2;
            for (int j = 1; j < i; j++) {
                System.out.print(x);
                x--;
            }
            System.out.println();
        }
    }

    public void pattern18(int n) {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i=n; i >= 1; i--){
            for (int j=i; j <= n; j++){
                System.out.print(str.charAt(j-1) + " ");
            }
            System.out.println();
        }
    }

    public void pattern19(int n) {
        for (int i = 1; i<=n ; i++) {
            for (int j = n; j >= i; j--){
                System.out.print("*");
            }
            for (int j = 1; j < 2*i-1;j++) {
                System.out.print(" ");
            }
            for (int j = 2*n-i; j >= n; j--) {
                System.out.print("*");
            }
            System.out.println();
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            for (int j = 2*(n - i); j >= 1; j--) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public void pattern20(int n) {
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            for (int j = 2*(n - i); j >= 1; j--) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = 1; i<=n ; i++) {
            for (int j = n; j >= i; j--){
                System.out.print("*");
            }
            for (int j = 1; j < 2*i-1;j++) {
                System.out.print(" ");
            }
            for (int j = 2*n-i; j >= n; j--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public void pattern21(int n) {
        for (int i =1 ;i<=n;i++) {
            if ( i == 1 || i == n) {
                for (int j = 1; j <= n; j++) {
                    System.out.print("*");
                }
            } else {
                for (int j = 1; j <= n; j++){
                    if (j == 1 || j == n) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }

    public void pattern22(int n) {
        for (int i = 1; i <= 2 * n - 1; i++) {
            for (int j = 1; j <= 2 * n - 1; j++) {
                int min = Math.min(Math.min(i, j), Math.min(2 * n - i, 2 * n - j));
                System.out.print(n - min + 1 + " ");
            }
            System.out.println();
        }
    }
}
