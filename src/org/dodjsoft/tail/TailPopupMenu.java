/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dodjsoft.tail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Richard Dodd
 */
public class TailPopupMenu extends JPopupMenu {

    private final JMenuItem newMenuItem;
    private final JMenuItem deleteMenuItem;
    private TailFilePanel currentPane = null;
    private final ActionListener menuListener;

    public TailPopupMenu(final TailTopComponent parent) {
        super();
        menuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // TODO probably shouldn't do this with string
                String act = event.getActionCommand();
                if(null != act) switch (act) {
                    case "New":
                        parent.showNewTail();
                        break;
                    case "Delete":
                        JMenuItem item = (JMenuItem) event.getSource();
                        TailPopupMenu menu = (TailPopupMenu) item.getParent();
                        parent.removeTail(menu.getCurrentPane());
                        break;
                }
            }
        };
        newMenuItem = new JMenuItem("New");
        newMenuItem.addActionListener(menuListener);
        this.add(newMenuItem);
        deleteMenuItem = new JMenuItem("Delete");
        deleteMenuItem.addActionListener(menuListener);
        this.add(deleteMenuItem);
    }

    public void setCurrentPane(TailFilePanel pane) {
        currentPane = pane;
    }

    /**
     * @return the currentPane
     */
    public TailFilePanel getCurrentPane() {
        return currentPane;
    }
}
