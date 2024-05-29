/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cyberhub;

import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;

/**
 *
 * @author duran
 */
public class CyberHub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
                //UIManager.setLookAndFeel( new FlatLightLaf() );
                FlatDraculaIJTheme.setup();
            } catch( Exception ex ) {
                  System.err.println( "Failed to initialize LaF" );
        }
       login u = new login();
       u.setVisible(true);
    }
    
}
