package leetcode.java;

// 302. Smallest Rectangle Enclosing Black Pixels
// https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/

// 大弓 O(mlogn) + O(nlogm)
public class LC302_Smallest_Rectangle_Enclosing_Black_Pixels {
    public int minArea(char[][] image, int x, int y) {
        if(image == null || image.length == 0
                || image[0] == null || image[0].length == 0) {
            return 0;
        }

        int row = image.length, col = image[0].length;

        // a combination of "find range" and horizontal/vertical scan
        int left = getFirstIndex(image, 0, y, true);
        int right = getLastIndex(image, y, col - 1, true);
        int top = getFirstIndex(image, 0, x, false);
        int bottom = getLastIndex(image, x, row - 1, false);

        return (right - left + 1) * (bottom - top + 1);

    }

    private int getFirstIndex(char[][] image, int start, int end, boolean isHorizontal){
        int left = start, right = end;

        while(left + 1 < right){
            int mid = left + (right - left)/2;
            if(hasBlack(image, mid, isHorizontal)){
                right = mid;
            }else{
                left = mid;
            }
        }
        return hasBlack(image, left, isHorizontal) ? left : right;
    }

    private int getLastIndex(char[][] image, int start, int end, boolean isHorizontal){
        int left = start, right = end;

        while(left + 1 < right){
            int mid = left + (right - left)/2;
            if(hasBlack(image, mid, isHorizontal)){
                left = mid;
            }else{
                right = mid;
            }
        }
        return hasBlack(image, right, isHorizontal) ? right : left;
    }

    // To check scan the matrix horizontally/vertically.
    private boolean hasBlack(char[][]image, int mid, boolean isHorizontal){
        if(isHorizontal){
            for(int i = 0; i < image.length; i++){
                if(image[i][mid] == '1') return true;
            }
            return false;
        }else{
            for(int j = 0; j < image[0].length; j++){
                if(image[mid][j] == '1') return true;
            }
            return false;
        }
    }
}

// 算法加强
// 从(x, y)点，向上下左右分别作binary search, 找到四个边界
// O(mlogn) + O(nlogm)
class LC302 {
    public int minArea(char[][] image, int x, int y) {
        if(image == null || image.length == 0
                || image[0] == null || image[0].length == 0) {
            return 0;
        }

        int row = image.length, col = image[0].length;
        int start = 0, end = 0;

        // 最终的边界
        int left = 0, right = 0, top = 0, bottom = 0;

        // left: 0 ~ y
        start = 0;
        end = y;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int i = 0;
            for (i = 0; i < row; i++) {
                if (image[i][mid] == '1') {
                    end = mid - 1;
                    break;
                }
            }
            if (i == row) start = mid + 1;
        }
        left = start;

        // right: y ~ col - 1
        start = y;
        end = col - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int i = 0;
            for (i = 0; i < row; i++) {
                if (image[i][mid] == '1') {
                    start = mid + 1;
                    break;
                }
            }
            if (i == row) end = mid - 1;
        }
        right = end;

        // top: 0 ~ x
        start = 0;
        end = x;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int j = 0;
            for (j = 0; j < col; j++) {
                if (image[mid][j] == '1') {
                    end = mid - 1;
                    break;
                }
            }
            if (j == col) start = mid + 1;
        }
        top = start;

        // bottom: x ~ row - 1
        start = x;
        end = row - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int j = 0;
            for (j = 0; j < col; j++) {
                if (image[mid][j] == '1') {
                    start = mid + 1;
                    break;
                }
            }
            if (j == col) end = mid - 1;
        }
        bottom = end;

        return (right - left + 1) * (bottom - top + 1);
    }
}
