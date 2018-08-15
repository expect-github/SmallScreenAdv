package com.hyt.advsmallscreen.domain.adv;


import com.hyt.advsmallscreen.domain.data.advData.AdvBaseData;


/**
 * Created by Tao on 2018/8/10 0010.
 */

public class AdvDataPullHelper {
    AdvBaseData.AdvDataType lastDataType;
    AdvBaseData.AdvMediaType lastMediatypeType;

    public PullAcvData nextPullAdv(AdvBaseData baseData) {
        AdvBaseData.AdvDataType dataType;
        AdvBaseData.AdvMediaType advType;
        if (baseData !=null) {
       dataType = baseData.getDataType();
          advType  = baseData.getAdvMediaType();
        }else {
            dataType = AdvBaseData.AdvDataType.hyt;
            advType  = AdvBaseData.AdvMediaType.video;
        }
        return nextDataType(dataType, advType);
    }
    
    public PullAcvData nextPullAdv(PullAcvData baseData) {
        
        AdvBaseData.AdvDataType dataType = baseData.getDataType();
        AdvBaseData.AdvMediaType advType = baseData.getMediaType();
        
        return nextDataType(dataType, advType);
    }
    

    private PullAcvData nextDataType(AdvBaseData.AdvDataType dataType, AdvBaseData.AdvMediaType advType) {
        advType = AdvBaseData.AdvMediaType.video ;
        switch (dataType) {
            case local:
                dataType = AdvBaseData.AdvDataType.hyt ;
                break;
                
            case hyt:
                dataType = AdvBaseData.AdvDataType.baidu ;
                break;

            case baidu:
                dataType = AdvBaseData.AdvDataType.sens ;
                break;

            case sens:
                dataType = AdvBaseData.AdvDataType.yishou ;
                break;

            case yishou:
                dataType = AdvBaseData.AdvDataType.local ;
                break;
        }

        return new PullAcvData(dataType ,advType);
    }


    public class PullAcvData {

        AdvBaseData.AdvDataType dataType;
        AdvBaseData.AdvMediaType mediaType;

        public AdvBaseData.AdvDataType getDataType() {
            return dataType;
        }

        public void setDataType(AdvBaseData.AdvDataType dataType) {
            this.dataType = dataType;
        }

        public AdvBaseData.AdvMediaType getMediaType() {
            return mediaType;
        }

        public void setMediaType(AdvBaseData.AdvMediaType mediaType) {
            this.mediaType = mediaType;
        }

        PullAcvData(AdvBaseData.AdvDataType dataType, AdvBaseData.AdvMediaType mediaType) {
            this.dataType = dataType;
            this.mediaType = mediaType;
        }

        @Override
        public String toString() {
            return "PullAcvData{" +
                    "dataType=" + dataType.getType() +
                    ", mediaType=" + mediaType.getType() +
                    '}';
        }
    }
}
