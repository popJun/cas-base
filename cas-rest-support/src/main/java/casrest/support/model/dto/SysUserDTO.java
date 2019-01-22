package casrest.support.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * {“@class”:”org.apereo.cas.authentication.principal.SimplePrincipal”,
 * ”id”:”casuser”,”attributes”:{}}
 */
public class SysUserDTO {

    @JsonProperty("id")
    private Long id;
    @NotNull
    private String username;
    /**
     * 需要返回实现org.apereo.cas.authentication.principal.Principal的类名接口
     */
    @JsonProperty("@class")
    //需要返回实现org.apereo.cas.authentication.principal.Principal的类名接口
    private String clazz = "org.apereo.cas.authentication.principal.SimplePrincipal";
    @JsonProperty("attributes")
    private Map<String,Object> attributes = new HashMap<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @JsonIgnore
    public SysUserDTO addAttribute(String key, Object val) {
        getAttributes().put(key, val);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
