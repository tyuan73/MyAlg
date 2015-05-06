/*
 ============================================================================
 Name        : testc.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <string.h>

#define VAL (0)
#define ACT (1)
#define LEFT(idx) (((idx) << 1) + 1)
#define RIGHT(idx) (((idx) << 1) + 2)

char str[1025000];
int st[5000000][2];

void buildST(int idx, int l, int r) {
    int mid = (l + r) / 2;

    st[idx][ACT] = 0;
    if (l == r) {
        st[idx][VAL] = str[l] == '1' ? 1 : 0;
        return;
    }

    buildST(LEFT(idx), l, mid);
    buildST(RIGHT(idx), mid + 1, r);

    st[idx][VAL] = st[LEFT(idx)][VAL] + st[RIGHT(idx)][VAL];
}

void pushUpdate(int idx, int l, int r, int act) {
    if (act == 1) {
        st[idx][VAL] = 0;
        st[idx][ACT] = 1;
    } else if (act == 2) {
        st[idx][VAL] = r - l + 1;
        st[idx][ACT] = 2;
    } else if (act == 3) {
        st[idx][VAL] = (r - l + 1) - st[idx][VAL];
        st[idx][ACT] = 3 - st[idx][ACT];
    }
    if (l == r)
        st[idx][ACT] = 0;
}

void update(int idx, int l, int r, int rl, int rr, int act) {
    int cur, mid = (l + r) / 2;
    if (rr < l || r < rl)
        return;

    if (rl <= l && r <= rr) {
        pushUpdate(idx, l, r, act);
        return;
    }

    cur = st[idx][ACT];
    if (cur != 0) {
        pushUpdate(LEFT(idx), l, mid, cur);
        pushUpdate(RIGHT(idx), mid + 1, r, cur);
        st[idx][ACT] = 0;
    }

    update(LEFT(idx), l, mid, rl, rr, act);
    update(RIGHT(idx), mid + 1, r, rl, rr, act);

    st[idx][VAL] = st[LEFT(idx)][VAL] + st[RIGHT(idx)][VAL];
}

int query(int idx, int l, int r, int rl, int rr) {
    int left, right, cur, mid = (l + r) / 2;
    if (rr < l || r < rl)
        return 0;

    if (rl <= l && r <= rr)
        return st[idx][VAL];

    cur = st[idx][ACT];
    if (cur != 0) {
        pushUpdate(LEFT(idx), l, mid, cur);
        pushUpdate(RIGHT(idx), mid + 1, r, cur);
        st[idx][ACT] = 0;
    }

    left = query(LEFT(idx), l, mid, rl, rr);
    right = query(RIGHT(idx), mid + 1, r, rl, rr);
    return left + right;
}

int main(void) {
    int a, b, qt, k, m, last, i;
    int tc = 1, t;
    char pat[80], op;

    scanf("%d", &t);
    for (tc = 1; tc <= t; tc++) {
        printf("Case %d:\n", tc);

        qt = 1;
        scanf("%d", &k);
        last = 0;
        for (i = 0; i < k; i++) {
            scanf("%d %s", &m, pat);
            while (m-- > 0) {
                int j = 0;
                while (pat[j])
                    str[last++] = pat[j++];
            }
        }
        last--;
        buildST(0, 0, last);

        scanf("%d", &m);
        while (m-- > 0) {
            scanf(" %c %d %d", &op, &a, &b);
            if (op == 'E') {
                update(0, 0, last, a, b, 1);
            } else if (op == 'F') {
                update(0, 0, last, a, b, 2);
            } else if (op == 'I') {
                update(0, 0, last, a, b, 3);
            } else {
                printf("Q%d: %d\n", qt++, query(0, 0, last, a, b));
            }
        }
    }

    return 0;
}
