
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yassine
 */
class myPanel extends JPanel
{
    ArrayList pointList;
    Color selectedColor;
    Ellipse2D selectedPoint;
  
    public myPanel()
    {
        pointList = new ArrayList();
        selectedColor = Color.red;
        //addMouseListener(new PointLocater(this));
        setBackground(Color.black);
    }
  
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        Ellipse2D e;
        Color color;
        for(int j = 0; j < pointList.size(); j++)
        {
            e = (Ellipse2D)pointList.get(j);
            if(e == selectedPoint)
                color = selectedColor;
            else
                color = Color.blue;
            g2.setPaint(color);
            g2.fill(e);
        }
    }
  
    public ArrayList getPointList()
    {
        return pointList;
    }
  
    public void setSelectedPoint(Ellipse2D e)
    {
        selectedPoint = e;
        repaint();
    }
  
    public void addPoint(Point p)
    {
        Ellipse2D e = new Ellipse2D.Double(p.x - 3, p.y - 3, 6, 6);
        pointList.add(e);
        selectedPoint = null;
        repaint();
    }
}
