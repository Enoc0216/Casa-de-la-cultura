/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlReportes;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import casa_cultura.Casa_Cultura;

/**
 *
 * @author zahori
 */
public class ControlReporteImparte {
      public ControlReporteImparte(){
    verReporte();
    }
    private void verReporte(){
    JasperReport report;
    JasperPrint re;
    
    try{
        URL in= this.getClass().getResource("/Reportes/rptImparteG.jasper");
        report =(JasperReport)JRLoader.loadObject(in);
        Map parametros=new HashMap();
        
        re= JasperFillManager.fillReport(report,parametros,Casa_Cultura.mysql.getConexion());
        JasperViewer.viewReport(re,false);
    }catch(JRException e){
    e.printStackTrace();
    }
    }
    
}
