@WebServlet("/supplier")
public class SupplierController extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String city = req.getParameter("city");

        String sql = "INSERT INTO suppliers(name, city) VALUES (?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, city);
            ps.executeUpdate();

            resp.getWriter().print("Supplier created successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
