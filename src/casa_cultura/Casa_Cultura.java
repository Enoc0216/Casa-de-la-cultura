/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casa_cultura;

import Util.Util;
import datos.MySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.Utilerias;
import vistas.FrmLogin;

/**
 *
 * @author LEMUEL
 */
public class Casa_Cultura {

    /**
     * @param args the command line arguments
     */
        public static MySQL mysql = new MySQL();
           public static Utilerias u = new Utilerias();
            public static Util ul = new Util();
            
    public static void main(String[] args) {
                    new FrmLogin().setVisible(true);
                    u.creaCarpeta("C:\\Casa_Cultura");
                       ul.creaCarpeta("C:\\Casa");

    }
    
}
