package com.jbit.dao;

import com.jbit.entity.AppInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppInfoMapper {

    int insertApp(AppInfo appinfo);

    int UpdateAppVersion(@Param("vid") Long vid, @Param("aid") Long aid);

    int insertSelective(AppInfo record);

    AppInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppInfo record);

    int updateAppinfo(AppInfo appInfo);

    /**
     * 条件查询所有APP信息
     * @return
     */
    List<AppInfo> findAppList(@Param("queryStatus") Integer queryStatus,@Param("querySoftwareName") String querySoftwareName, @Param("queryFlatformId") Integer queryFlatformId, @Param("queryCategoryLevel1") Integer queryCategoryLevel1, @Param("queryCategoryLevel2") Integer queryCategoryLevel2, @Param("queryCategoryLevel3") Integer queryCategoryLevel3, @Param("first") Integer first, @Param("last") Integer last);
    List<AppInfo> findBackendAppList(@Param("querySoftwareName") String querySoftwareName, @Param("queryFlatformId") Integer queryFlatformId, @Param("queryCategoryLevel1") Integer queryCategoryLevel1, @Param("queryCategoryLevel2") Integer queryCategoryLevel2, @Param("queryCategoryLevel3") Integer queryCategoryLevel3, @Param("first") Integer first, @Param("last") Integer last);

    /**
     * 查询单个APP
     * @param aid
     * @return
     */
    AppInfo findAppOne(Long aid);

    /**
     * 计算APP条数
     * @return
     */
    Integer totalAppCount(@Param("queryStatus") Integer queryStatus,@Param("querySoftwareName") String querySoftwareName, @Param("queryFlatformId") Integer queryFlatformId, @Param("queryCategoryLevel1") Integer queryCategoryLevel1, @Param("queryCategoryLevel2") Integer queryCategoryLevel2, @Param("queryCategoryLevel3") Integer queryCategoryLevel3);
    Integer totalBackendAppCount(@Param("querySoftwareName") String querySoftwareName, @Param("queryFlatformId") Integer queryFlatformId, @Param("queryCategoryLevel1") Integer queryCategoryLevel1, @Param("queryCategoryLevel2") Integer queryCategoryLevel2, @Param("queryCategoryLevel3") Integer queryCategoryLevel3);

    /**
     * 审核通过或不通过
     * @return
     */
    Integer upStatus(@Param("id") Long id, @Param("status") Long status);

    Integer upStatusSale(AppInfo appInfo);
    /**
     * 异步检查AKPName
     * @param apkName
     * @return
     */
    AppInfo findAPKName(String apkName);

    Integer delApp(Long id);

    Integer updateApp(Long id);
}