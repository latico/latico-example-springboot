package com.latico.example.springboot.bean.dto;

import lombok.Data;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-06-20 15:25
 * @Version: 1.0
 */
@Data
public class Result {
    private Integer id;
    private String username;
    private String shopName;
    private String categoryName;
    private String brandName;
    private String shopId;
    private String omit;
    private String updateTime;
    private boolean deleteStatus;
    private String createTime;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getOmit() {
        return omit;
    }

    public void setOmit(String omit) {
        this.omit = omit;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", shopName='").append(shopName).append('\'');
        sb.append(", categoryName='").append(categoryName).append('\'');
        sb.append(", brandName='").append(brandName).append('\'');
        sb.append(", shopId='").append(shopId).append('\'');
        sb.append(", omit='").append(omit).append('\'');
        sb.append(", updateTime='").append(updateTime).append('\'');
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", createTime='").append(createTime).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
