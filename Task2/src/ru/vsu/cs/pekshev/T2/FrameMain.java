package ru.vsu.cs.pekshev.T2;

import ru.vsu.cs.pekshev.util.ArrayUtils;
import ru.vsu.cs.pekshev.util.JTableUtils;
import ru.vsu.cs.pekshev.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JTable tableInput1;
    private JButton buttonRandomInput;
    private JTable tableOutput;
    private JScrollPane scrollPaneTableOutput;
    private JScrollPane scrollPaneTableInput1;
    private JButton buttonExecute;
    private JTable tableInput2;
    private JScrollPane scrollPaneTableInput2;

    private final JFileChooser FILE_CH00SER_OPEN = new JFileChooser();
    private final JFileChooser FILE_CHOOSER_SAVE = new JFileChooser();

    public FrameMain() {
        this.setTitle("FrameMain");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableInput1, 40, false,
                true, false, true);
        JTableUtils.initJTableForArray(tableInput2, 40, false,
                true, false, true);
        JTableUtils.initJTableForArray(tableOutput, 40, false,
                true, false, true);
        tableInput1.setRowHeight(25);
        tableInput2.setRowHeight(25);
        tableOutput.setRowHeight(25);
        scrollPaneTableInput1.setPreferredSize(new Dimension(-1, 90));
        scrollPaneTableInput2.setPreferredSize(new Dimension(-1, 90));
        scrollPaneTableOutput.setPreferredSize(new Dimension(-1, 90));


        FILE_CH00SER_OPEN.setCurrentDirectory(new File("."));
        FILE_CHOOSER_SAVE.setCurrentDirectory(new File("."));

        final FileFilter FILE_NAME_TXT_EXTENSION_FILTER = new FileNameExtensionFilter("Text files",
                "txt");

        FILE_CH00SER_OPEN.addChoosableFileFilter(FILE_NAME_TXT_EXTENSION_FILTER);
        FILE_CHOOSER_SAVE.addChoosableFileFilter(FILE_NAME_TXT_EXTENSION_FILTER);

        FILE_CHOOSER_SAVE.setAcceptAllFileFilterUsed(false);
        FILE_CHOOSER_SAVE.setDialogType(JFileChooser.SAVE_DIALOG);
        FILE_CHOOSER_SAVE.setApproveButtonText("Save");

        addFillWithRandomButton();
        addExecuteButton();
    }


    private void addFillWithRandomButton() {
        buttonRandomInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[] arr1 = ArrayUtils.createRandomIntArray(tableInput1.getColumnCount(), 100);
                    JTableUtils.writeArrayToJTable(tableInput1, arr1);
                    int[] arr2 = ArrayUtils.createRandomIntArray(tableInput2.getColumnCount(), 100);
                    JTableUtils.writeArrayToJTable(tableInput2, arr2);
                } catch (Exception e) {
                    SwingUtils.showInfoMessageBox("Enter integer list please");
                }
            }
        });
    }

    private void addExecuteButton() {
        buttonExecute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[] arr1 = JTableUtils.readIntArrayFromJTable(tableInput1);
                    int[] arr2 = JTableUtils.readIntArrayFromJTable(tableInput2);
                    SimpleLinkedList<Integer> inputList1 = new SimpleLinkedList<>();
                    SimpleLinkedList<Integer> inputList2 = new SimpleLinkedList<>();
                    for (int j : arr1) {
                        inputList1.addLast(j);
                    }
                    for (int j : arr2) {
                        inputList2.addLast(j);
                    }
                    SimpleLinkedList<Integer> answerList = Execution.execute(inputList1,inputList2);
                    int[] answerArr = new int[answerList.size()];
                    for (int i = 0; i < answerList.size(); i++) {
                        answerArr[i] = answerList.get(i);
                    }

                    JTableUtils.writeArrayToJTable(tableOutput, answerArr);
                } catch (Exception e) {
                    SwingUtils.showInfoMessageBox("Enter integer list please");
                }
            }
        });
    }
}
