@WebServlet("/inventory")
public class InventoryListController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        String sql =
            "SELECT i.id, s.name AS supplier, i.product_name, i.quantity, i.price " +
            "FROM inventory i JOIN suppliers s ON i.supplier_id = s.id";

        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                resp.getWriter().println(
                    rs.getString("supplier") + " | " +
                    rs.getString("product_name") + " | " +
                    rs.getInt("quantity") + " | " +
                    rs.getDouble("price")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
