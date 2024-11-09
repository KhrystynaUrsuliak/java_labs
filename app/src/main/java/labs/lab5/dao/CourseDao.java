//package labs.lab5.dao;
//
//import labs.lab5.model.Course;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CourseDao {
//    private final Connection connection;
//
//    public CourseDao() {
//        try {
//            connection = DataSource.getConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public Course add(Course course) throws SQLException {
//        String query = "INSERT INTO Course (name, language_id, level, start_date, end_date, price) VALUES (?, ?, ?, ?, ?, ?)";
//        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
//            ps.setString(1, course.getName());
//            ps.setInt(2, course.getLanguage().getId());
//            ps.setString(3, course.getLevel());
//            ps.setDate(4, Date.valueOf(course.getStartDate()));
//            ps.setDate(5, Date.valueOf(course.getEndDate()));
//            ps.setDouble(6, course.getPrice());
//            ps.executeUpdate();
//
//            ResultSet keys = ps.getGeneratedKeys();
//            if (keys.next()) {
//                course.setId(keys.getInt(1)); // Залежно від реалізації
//            }
//            return course;
//        }
//    }
//
//    public List<Course> getAll() throws SQLException {
//        String query = "SELECT * FROM Course";
//        List<Course> courses = new ArrayList<>();
//        try (Statement st = connection.createStatement();
//             ResultSet rs = st.executeQuery(query)) {
//            while (rs.next()) {
//                courses.add(fromResultSet(rs));
//            }
//        }
//        return courses;
//    }
//
//    public Course getById(int id) throws SQLException {
//        String query = "SELECT * FROM Course WHERE id = ?";
//        try (PreparedStatement ps = connection.prepareStatement(query)) {
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return fromResultSet(rs);
//            }
//            throw new SQLException("Course with ID " + id + " not found.");
//        }
//    }
//
//    public boolean update(Course course) throws SQLException {
//        String query = "UPDATE Course SET name = ?, level = ?, start_date = ?, end_date = ?, price = ? WHERE id = ?";
//        try (PreparedStatement ps = connection.prepareStatement(query)) {
//            ps.setString(1, course.getName());
//            ps.setString(2, course.getLevel());
//            ps.setDate(3, Date.valueOf(course.getStartDate()));
//            ps.setDate(4, Date.valueOf(course.getEndDate()));
//            ps.setDouble(5, course.getPrice());
//            ps.setInt(6, course.getId());
//            return ps.executeUpdate() > 0;
//        }
//    }
//
//    public boolean deleteById(int id) throws SQLException {
//        String query = "DELETE FROM Course WHERE id = ?";
//        try (PreparedStatement ps = connection.prepareStatement(query)) {
//            ps.setInt(1, id);
//            return ps.executeUpdate() > 0;
//        }
//    }
//
//    private Course fromResultSet(ResultSet rs) throws SQLException {
//        return new Course(
//            rs.getString("name"),
//            rs.getInt("language_id"),
//            rs.getString("level"),
//            rs.getDate("start_date").toLocalDate(),
//            rs.getDate("end_date").toLocalDate(),
//            rs.getDouble("price")
//        );
//    }
//}
