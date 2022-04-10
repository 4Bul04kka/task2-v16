package ru.vsu.cs.cource1;

import ru.vsu.cs.util.ArrayUtils;
import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import java.util.function.Consumer;


public class MainFrame extends JFrame {
    public static final int EXPORT_WIDTH = 800;
    public static final int EXPORT_HEIGHT = 600;

    private JButton buttonSample1;
    private JButton buttonRandom1;
    private JButton buttonRandom2;
    private JTable tableArr;
    private JButton buttonDeleteDuplicates;
    private JPanel panelMain;
    private JPanel panelPerformance;
    private JCheckBox checkBoxPartiallyOrdered;

    private JFileChooser fileChooserSave;


    public MainFrame() {
        this.setTitle("Сортировки");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableArr, 60, false, true, false, true);
        tableArr.setRowHeight(30);


        buttonDeleteDuplicates.setVisible(true);

        // привязка обработчиков событий

        buttonSample1.addActionListener(actionEvent -> {
            int[] arr = {3, 8, 2, 2, 6, 9, 9, 7, 0, 9};
            JTableUtils.writeArrayToJTable(tableArr, arr);
        });
        buttonRandom1.addActionListener(actionEvent -> {
            int[] arr = ArrayUtils.createRandomIntArray(10, 100);
            JTableUtils.writeArrayToJTable(tableArr, arr);
        });
        buttonRandom2.addActionListener(actionEvent -> {
            int[] arr = ArrayUtils.createRandomIntArray(15, 100);
            JTableUtils.writeArrayToJTable(tableArr, arr);
        });

        buttonDeleteDuplicates.addActionListener(actionEvent -> {
            try {
                int [] arr = JTableUtils.readIntArrayFromJTable(tableArr);
                SimpleLinkedList<Integer> list = new SimpleLinkedList();
                list.copyArray(ArrayUtils.toObject(arr));

               // SimpleLinkedList list = SimpleLinkedList.toList(arr);
                list.removeDuplicates();
                JTableUtils.writeArrayToJTable(tableArr, toArray(list));

            } catch (Exception ex) {
                SwingUtils.showErrorMessageBox(ex);
            }
        });



        buttonSample1.doClick();
    }
    public int[] toArray(SimpleLinkedList<Integer> list) throws Exception {
        int[] a = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            a[i] = (int) list.get(i);
        }
        return a;
    }

    public static boolean checkSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    private void sortDemo(Consumer<Integer[]> sort) {
        try {
            Integer[] arr = ArrayUtils.toObject(JTableUtils.readIntArrayFromJTable(tableArr));

            sort.accept(arr);

            int[] primiviteArr = ArrayUtils.toPrimitive(arr);
            JTableUtils.writeArrayToJTable(tableArr, primiviteArr);

            // проверка правильности решения
            assert primiviteArr != null;
            if (!checkSorted(primiviteArr)) {
                // надеюсь, это невозможный сценарий
                SwingUtils.showInfoMessageBox("Упс... А массив-то неправильно отсортирован!");
            }
        } catch (Exception ex) {
            SwingUtils.showErrorMessageBox(ex);
        }
    }

}
