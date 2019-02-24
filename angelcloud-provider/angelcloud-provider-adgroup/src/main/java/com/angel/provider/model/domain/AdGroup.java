package com.angel.provider.model.domain;

import com.angel.base.model.domain.BaseEntity;
import com.angel.provider.model.dto.AdGroupDto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * 广告组表
 */
@Data
public class AdGroup extends BaseEntity implements Serializable {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 广告组分类ID
     */
    private Integer typeId;

    /**
     * 名称
     */
    private String name;

    /**
     * 标识
     */
    private String adKey;

    /**
     * 备注
     */
    private String remake;

    /**
     * 分组类型
     */
    @TableField(exist = false)
    private AdGroupType adGroupType;

    public AdGroupDto convertTo(){
        AdGroupDTOConvert adGroupDTOConvert = new AdGroupDTOConvert();
        AdGroupDto convert = adGroupDTOConvert.convert(this);
        return convert;
    }

    public AdGroup convertFor(AdGroupDto adGroupDto){
        AdGroupDTOConvert adGroupDTOConvert = new AdGroupDTOConvert();
        AdGroup convert = adGroupDTOConvert.reverse().convert(adGroupDto);
        return convert;
    }

    private static class AdGroupDTOConvert extends Converter<AdGroup, AdGroupDto> {

        @Override
        protected AdGroupDto doForward(AdGroup adGroup) {
            AdGroupDto adGroupDto = new AdGroupDto();
            BeanUtils.copyProperties(adGroup, adGroupDto);
            return adGroupDto;
        }

        @Override
        protected AdGroup doBackward(AdGroupDto adGroupDto) {
            AdGroup adGroup = new AdGroup();
            BeanUtils.copyProperties(adGroupDto, adGroup);
            return adGroup;
        }
    }

}