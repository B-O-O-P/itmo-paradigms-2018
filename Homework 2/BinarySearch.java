package search;

/**
 * Simple implementaion of binary search.
 */
public class BinarySearch {

    //a[l]=∞; a[r]=-∞;
    //pre: ∀i,j: i<j;a[i]>=a[j] (массив а - невозрастающий)
    static int binsearch(int a[], int x) {
        //pre:∀i,j: i<j;a[i]>=a[j]
        int l = -1;
        //post:l==-1 && l==l' && ∀i,j: i<j;a[i]>=a[j]

        //pre:l==-1 && l==l' && ∀i,j: i<j;a[i]>=a[j]
        int r = a.length;
        //post:r == a.length() && r==r' && l==-1 && l==l' && ∀i,j: i<j;a[i]>=a[j]

        //pre:r == a.length() && r==r' && l==-1 && l==l' && ∀i,j: i<j;a[i]>=a[j]
        while (l < r - 1) {
            int m = (l + r) / 2;
            //pre: l<r-1 && m==(l+r)/2 && min(i |a[i]<=x) ∈ [l';r']
            if (a[m] > x) {
                //pre:m==0 && a[m']>x ⇒ min(i |a[i]<=x) ∈ [m';r']
                l = m;
                //post:l==0 && a[l']>x && min(i |a[i]<=x) ∈ [l';r']
            } else {
                //pre:m==1 && a[m']<=x ⇒  min(i |a[i]<=x) ∈ [l';m']
                r = m;
                //post:r==1 && a[r']<=x && min(i |a[i]<=x) ∈ [l';r']
            }
            //post:l<r-1 && min(i |a[i]<=x) ∈ [l';r'] && a[l'] > x >= a[r']
        }
        //post:1>=r'-l' &&  min(i |a[i]<=x) ∈ [l';r'] && a[l'] > x >= a[r']
        //post: ⇒ r'=min (i |a[i]<=x)

        //pre:r'=min (i |a[i]<=x)
        return r;
    }
    //post: a'==a && ∀i,j: i<j;a[i]>=a[j]

    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int a[] = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            a[i - 1] = Integer.parseInt(args[i]);
        }
        System.out.print(binsearch(a, x));
    }
}
