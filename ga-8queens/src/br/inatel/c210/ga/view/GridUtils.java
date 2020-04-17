package br.inatel.c210.ga.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.inatel.c210.ga.entity.Chromossome;

public class GridUtils
{
    public static void show(Chromossome c)
    {
        Integer[] genes = c.getGenes();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 8));
        Font font = new Font("Arial", Font.PLAIN, 48);
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                JLabel label = new JLabel();
                if ((i % 2 == 0 && j % 2 != 0) || (i % 2 != 0 && j % 2 == 0))
                    label.setBackground(Color.WHITE);
                else
                    label.setBackground(Color.LIGHT_GRAY);
                label.setFont(font);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);
                label.setOpaque(true);
                label.setText(i == genes[j] ? "♛" : "");
                panel.add(label);
            }
        }

        JFrame frame = new JFrame();
        frame.setBounds(160, 160, 640, 480);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Solution visualization");
        frame.setVisible(true);
    }
}
