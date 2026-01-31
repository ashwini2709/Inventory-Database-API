@WebServlet("/inventory")
public class InventoryController extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        int supplierId = Integer.parseInt(req.getParameter("supplierId"));
        String productName = req.getParameter("productName");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        double price = Double.parseDouble(req.getParameter("price"));

        if (quantity < 0 || price <= 0) {
            resp.getWriter().print("Invalid quantity or price");
            return;
        }

        String sql =
            "INSERT INTO inventory (supplier_id, product_name, quantity, price) " +
            "VALUES (?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, supplierId);
            ps.setString(2, productName);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);

            ps.executeUpdate();
            resp.getWriter().print("Inventory added successfully");

        } catch (SQLException e) {
            resp.getWriter().print("Supplier does not exist");
        }
    }
}
