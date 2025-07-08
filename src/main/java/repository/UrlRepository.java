package repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import model.Url;

@Repository
public class UrlRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Url url) {
        String sql = "INSERT INTO urls (original_url, short_code) VALUES (?, ?)";
        jdbcTemplate.update(sql, url.getOriginalUrl(), url.getShortCode());
    }

    public Url findByShortCode(String shortCode) {
        String sql = "SELECT * FROM urls WHERE short_code = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{shortCode}, (rs, rowNum) -> {
            Url url = new Url();
            url.setId(rs.getInt("id"));
            url.setOriginalUrl(rs.getString("original_url"));
            url.setShortCode(rs.getString("short_code"));
            return url;
        });
    }
}
