package com.atguigu.gmall.product.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lfy
 * @Description spu保存的数据
 * @create 2022-12-02 10:15
 */
@NoArgsConstructor
@Data
public class SpuSaveInfoVo {


    @JsonProperty("id")
    private Long id;
    @JsonProperty("spuName")
    private String spuName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("category3Id")
    private Long category3Id;
    @JsonProperty("tmId")
    private Long tmId;
    //以上信息保存到 spu_info 表中



    @JsonProperty("spuImageList")
    private List<SpuImageListDTO> spuImageList;
    @JsonProperty("spuSaleAttrList")
    private List<SpuSaleAttrListDTO> spuSaleAttrList;

    @NoArgsConstructor
    @Data
    public static class SpuImageListDTO {
        @JsonProperty("imgName")
        private String imgName;
        @JsonProperty("imgUrl")
        private String imgUrl;
    }

    @NoArgsConstructor
    @Data
    public static class SpuSaleAttrListDTO {
        @JsonProperty("baseSaleAttrId")
        private Long baseSaleAttrId;
        @JsonProperty("saleAttrName")
        private String saleAttrName;
        @JsonProperty("spuSaleAttrValueList")
        private List<SpuSaleAttrValueListDTO> spuSaleAttrValueList;

        @NoArgsConstructor
        @Data
        public static class SpuSaleAttrValueListDTO {
            @JsonProperty("baseSaleAttrId")
            private Long baseSaleAttrId;
            @JsonProperty("saleAttrValueName")
            private String saleAttrValueName;
        }
    }
}
