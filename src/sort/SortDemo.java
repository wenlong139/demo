package sort;

/**
 * @Author qinwenlong
 * @Date 2021/10/4
 **/
public class SortDemo {
    public static void main(String[] args) {
        int[] array = new int[]{3, 8, 2, 5, 1, 2};
        bubbleSort(array);
        System.out.println(array);
    }

    public static int halfFind(int[] array,int target) {
        int lowIndex = 0;
        int highIndex = array.length-1;
        while (lowIndex <= highIndex) {
            int mid=(lowIndex+highIndex)/2;
            if (target==array[mid]) {
                return mid;
            } else if (target < array[mid]) {
                highIndex = mid - 1;
            } else {
                lowIndex=mid+1;
            }
        }
        return -1;
    }
    public static void selectSort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            int min = array[i];
            int index = i;
            for (int j = i + 1; j < length; j++) {
                if (array[j] < min) {
                    min=array[j];
                    index=j;
                }
            }
            swap(i,index,array);
        }
    }

    public static void bubbleSort(int[] array) {
        int length = array.length;
        // 外层控制循环次数
        for (int i = length-1; i >0; i--) {
            // 内层每次冒泡长度
            for (int j = 0; j < i; j++) {
                if (array[j + 1] < array[j]) {
                    swap(j + 1, j, array);
                }
            }
        }
    }

    public static void insertSort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int j = i; j > 0 && array[j - 1] > array[j]; j--) {
                swap(j, j - 1, array);
            }
        }
    }

    public static void quickSort(int[] array,int low,int high) {
        if (low >= high) {
            return;
        }
        int pivotIndex = partition(array, low, high);
        quickSort(array,low,pivotIndex-1);
        quickSort(array,pivotIndex+1,high);
    }

    public static int partition(int[] array, int startIndex, int endIndex) {
        int low = startIndex;
        int high= endIndex;
        int key = array[startIndex];

        while (low < high) {
            while (low<high&&array[high] >= key) {
                high--;
            }
            while (low<high&&array[low] <= key) {
                low++;
            }
            if (low<high){
                swap(low,high,array);
            }
        }
        swap(low,startIndex,array);
        return low;
    }
    public static void swap(int i, int j, int[] array) {
        if (i == j) {
            return;
        }
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }


    public static void mergeSort(int [] array,int left,int right) {
        if (left >= right) {
            return ;
        }
        int mid= left+(right-left)/2;
        mergeSort(array, left, mid);
        mergeSort(array,mid+1,right);
        merge(array,mid,left,right);
    }

    private static void merge(int[] array, int mid,int left, int right) {
        if(left>right){
            return;
        }
        int[]temp = new int[right-left+1];
        int i = left,j=mid+1;
        // 临时数组的下标
        int tempIndex = 0;
        while (i<=mid&&j<=right&&i<=j){
            if (array[i] < array[j]) {
                temp[tempIndex++] = array[i++];
            } else {
                temp[tempIndex++]=array[j++];
            }
        }
        while (i <= mid) {
            temp[tempIndex++] = array[i++];

        }
        while (j <= right) {
            temp[tempIndex++] = array[j++];
            j++;
        }
        for (int k = 0; k < tempIndex; k++) {
            array[left+k]=temp[k];
        }
    }
}
