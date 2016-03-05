
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yassine
 */
public class BezierJavaApp extends javax.swing.JFrame {

    /**
     * Creates new form BezierJavaApp
     */
    private ArrayList<Double> pointsList = new ArrayList<>();
    private BezierCurve bezierCurve = new BezierCurve();

    private boolean needLine = false;
    private Point x;
    private Point y;

    public BezierJavaApp() {

        initComponents();
        //jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        curvePanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        curvePanel.setBackground(Color.white);
        curvePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        curvePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                curvePanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout curvePanelLayout = new javax.swing.GroupLayout(curvePanel);
        curvePanel.setLayout(curvePanelLayout);
        curvePanelLayout.setHorizontalGroup(
            curvePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 574, Short.MAX_VALUE)
        );
        curvePanelLayout.setVerticalGroup(
            curvePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 369, Short.MAX_VALUE)
        );

        jButton1.setText("Draw Curve");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(curvePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jButton1)
                        .addGap(41, 41, 41)
                        .addComponent(clearButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(curvePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void curvePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_curvePanelMouseClicked

        double X = evt.getPoint().getX();
        double Y = evt.getPoint().getY();
        pointsList.add(X);
        pointsList.add(Y);

        if (!needLine) {
            needLine = true;
            x = new Point((int) X, (int) Y);

        } else {
            y = new Point((int) X, (int) Y);
            drawLine(x, y);
            x = y;

        }

        Graphics2D g = (Graphics2D) this.curvePanel.getGraphics();

        g.setColor(Color.red);
        g.drawRect((int) evt.getPoint().getX(), (int) evt.getPoint().getY(), 1, 1);
        this.curvePanel.revalidate();

    }//GEN-LAST:event_curvePanelMouseClicked
    private void drawLine(Point x, Point y) {
        Graphics2D g = (Graphics2D) curvePanel.getGraphics();
        g.setColor(Color.blue);
        g.drawLine((int) x.getX(), (int) x.getY(), (int) y.getX(), (int) y.getY());

    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Let 1000 be the number of the points to draw..
        final int POINTS_ON_CURVE = 1000;

        Graphics2D g = (Graphics2D) this.curvePanel.getGraphics();
        double[] ptind = new double[pointsList.size()];
        double[] p = new double[POINTS_ON_CURVE];
        copyTo(pointsList, ptind, pointsList.size());

        bezierCurve.Bezier2D(ptind, (POINTS_ON_CURVE) / 2, p);

        // We draw the points
        for (int i = 1; i != POINTS_ON_CURVE - 1; i += 2) {
            g.setColor(Color.magenta);
            g.drawRect((int) p[i + 1], (int) p[i], 1, 1);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    // Method called to reset everything and clean the jpanel

    private void reset() {
        this.needLine = false;
        curvePanel.updateUI();
        this.pointsList.clear();
    }

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed

        reset();
    }//GEN-LAST:event_clearButtonActionPerformed
    private void copyTo(ArrayList<Double> a, double[] b, int size) {
        for (int i = 0; i < size; i++) {
            b[i] = a.get(i);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BezierJavaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BezierJavaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BezierJavaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BezierJavaApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BezierJavaApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JPanel curvePanel;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}