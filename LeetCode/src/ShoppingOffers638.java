/**
 * Created by miaomiao on 7/8/17.
 */

import java.util.*;

public class ShoppingOffers638 {
    int min = 0;
    List<Integer> price = null;
    List<List<Integer>> special = null;
    List<Integer> needs = null;
    int n = 0;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        min = Integer.MAX_VALUE;
        this.price = price;
        this.special = special;
        this.needs = needs;
        n = price.size();
        shop(0, 0);
        return min;
    }

    private void shop(int idx, int total) {
        if (idx >= special.size()) {
            for (int i = 0; i < n; i++) {
                total += needs.get(i) * price.get(i);
            }
            min = Math.min(min, total);
            return;
        }
        List<Integer> sp = special.get(idx);
        for (int i = 0; true; i++) {
            if (!minus(sp, i)) {
                add(sp, i);
                return;
            }
            shop(idx + 1, total + sp.get(n) * i);
            add(sp, i);
        }
    }

    // minus "special" from "needs". if "needs" is invalid, return false; otherwise true.
    private boolean minus(List<Integer> sp, int times) {
        boolean valid = true;
        for (int i = 0; i < n; i++) {
            needs.set(i, needs.get(i) - sp.get(i) * times);
            if (needs.get(i) < 0) valid = false;
        }
        return valid;
    }

    private void add(List<Integer> sp, int times) {
        for (int i = 0; i < n; i++)
            needs.set(i, needs.get(i) + sp.get(i) * times);
    }
}
