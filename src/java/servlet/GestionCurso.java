/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import controlador.ContenidoCursoController;
import controlador.CursoController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.curso;

/**
 *
 * @author Josue Emmanuel Medina Garcia
 */
public class GestionCurso extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if(request.getParameter("funcion").equals("ver")) {
            verContenido(request, response);
        }
        if(request.getParameter("funcion").equals("idCurso")) {
            idCurso(request, response);
        }
        if(request.getParameter("funcion").equals("obtenerCurso")) {
            obtenerCurso(request, response);
        }
        if(request.getParameter("funcion").equals("obtenerContenido")) {
            obtenerContenido(request, response);
        }
    }
    protected void verContenido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession ses = request.getSession();
        int idCurso = Integer.parseInt(request.getParameter("idCurso"));
        String nombreCurso = request.getParameter("nombreCurso");
        ses.setAttribute("idCurso", idCurso);
        ses.setAttribute("nombreCurso", nombreCurso);
    }
    
    protected void idCurso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession ses = request.getSession();
        int idCurso = Integer.parseInt(request.getParameter("idCurso"));
        ses.setAttribute("idCurso", idCurso);
    }
    
    protected void obtenerCurso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int idCurso = Integer.parseInt(request.getParameter("idCurso"));
        CursoController c = new CursoController();
        curso cur = c.getCurso(idCurso);
        Gson gson = new Gson();
        out.print(gson.toJson(cur));
    }
    protected void obtenerContenido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int idCurso = Integer.parseInt(request.getParameter("idCurso"));
        ContenidoCursoController c = new ContenidoCursoController();
        Gson gson = new Gson();
        out.print(gson.toJson(c.listarContenidoCurso(idCurso)));
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
