public class HeapSort {
        /**
        * Метод строит кучу из входного массива
        * извелкает элементы из кучи по одному
        * и тем самым сортирует входящий массив
        * @param arr
        */
        private static void sort(int arr[]) {
            int n = arr.length;

            // Построение кучи
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(arr, n, i);
            }

            //извлекаем элементы из кучи по одному
            for (int i=n-1; i>=0; i--) {
                // Перемещаем текущий корень в конец
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;

                // Вызываем процедуру heapify на уменьшенной куче
                heapify(arr, i, 0);
            }
        }

        /**
         * Метод для преобразования в двоичную кучу поддерева с корневым узлом i, что является
         * индексом в arr[]. n - размер кучи
         * @param arr
         * @param n
         * @param i
         */
        private static void heapify(int arr[], int n, int i) {
            int largest = i; // наибольший элемент как корень
            int l = 2*i + 1; //левый = 2*i + 1
            int r = 2*i + 2; //правый = 2*i + 2

            // Если левый дочерний элемент больше корня
            if (l < n && arr[l] > arr[largest]) {
                largest = l;
            }

            // Если правый дочерний элемент больше, чем самый большой элемент на данный момент
            if (r < n && arr[r] > arr[largest]) {
                largest = r;
            }
            // Если самый большой элемент не корень
            if (largest != i) {
                int swap = arr[i];
                arr[i] = arr[largest];
                arr[largest] = swap;

                // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
                heapify(arr, n, largest);
            }
        }


        public static void main(int[] array) {

            int n = array.length;

            HeapSort.sort(array);

            System.out.println("Sorted array: ");

            for (int i=0; i<n; ++i) {
                System.out.print(array[i] + " ");
            }
        }

}
