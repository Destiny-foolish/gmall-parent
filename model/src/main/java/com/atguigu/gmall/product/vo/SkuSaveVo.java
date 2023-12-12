package com.atguigu.gmall.product.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lfy
 * @Description
 * @create 2022-12-02 14:39
 */
@NoArgsConstructor
@Data
public class SkuSaveVo {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("spuId")
    private Long spuId;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("skuName")
    private String skuName;
    @JsonProperty("weight")
    private BigDecimal weight;
    @JsonProperty("skuDesc")
    private String skuDesc;
    @JsonProperty("category3Id")
    private Long category3Id;
    @JsonProperty("tmId")
    private Long tmId;
    @JsonProperty("skuDefaultImg")
    private String skuDefaultImg;


    @JsonProperty("skuImageList")
    private List<SkuImageListDTO> skuImageList;
    @JsonProperty("skuAttrValueList")
    private List<SkuAttrValueListDTO> skuAttrValueList;
    @JsonProperty("skuSaleAttrValueList")
    private List<SkuSaleAttrValueListDTO> skuSaleAttrValueList;


    @NoArgsConstructor
    @Data
    public static class SkuAttrValueListDTO {
        @JsonProperty("attrId")
        private Long attrId;
        @JsonProperty("valueId")
        private Long valueId;
    }

    @NoArgsConstructor
    @Data
    public static class SkuSaleAttrValueListDTO {
        @JsonProperty("saleAttrValueId")
        private Long saleAttrValueId;
        @JsonProperty("saleAttrValueName")
        private String saleAttrValueName;
        @JsonProperty("baseSaleAttrId")
        private Long baseSaleAttrId;
        @JsonProperty("saleAttrName")
        private String saleAttrName;
    }

    @NoArgsConstructor
    @Data
    public static class SkuImageListDTO {
        @JsonProperty("spuImgId")
        private Long spuImgId;
        @JsonProperty("imgName")
        private String imgName;
        @JsonProperty("imgUrl")
        private String imgUrl;
        @JsonProperty("isDefault")
        private String isDefault;
    }
}
