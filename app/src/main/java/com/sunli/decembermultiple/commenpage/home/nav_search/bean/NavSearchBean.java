package com.sunli.decembermultiple.commenpage.home.nav_search.bean;

import java.util.List;

/**
 * @Author sunli
 * @Data 2019/1/4
 */
public class NavSearchBean {

    /**
     * result : [{"commodityId":110,"commodityName":"轻松小熊系列 苹果手机自拍杆 三级美颜补光灯 苹果华为便携自拍","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/sjpj/4/1.jpg","price":98,"saleNum":0},{"commodityId":105,"commodityName":"小米8 256GB 全面屏 双频GPS智能拍照游戏手机","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/sj/6/1.jpg","price":3099,"saleNum":0},{"commodityId":120,"commodityName":"德米克 高端正品3d眼镜虚拟现实VR BOX眼镜二代定制LOGO手机家庭影院","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/yyyl/7/1.jpg","price":189,"saleNum":0},{"commodityId":101,"commodityName":"vivo X23幻彩版 多套餐 全面屏拍照美颜超大广角摄影水滴屏智能4G 正品vivox23手机","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/sj/2/1.jpg","price":2798,"saleNum":0},{"commodityId":112,"commodityName":"黑色重力支架 车载手机架汽车用导航支架车上支撑出风口汽车内多功能通用型金属车载支架","masterPic":"http://172.17.8.100/images/small/commodity/sjsm/sjpj/6/1.jpg","price":35,"saleNum":0}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 110
         * commodityName : 轻松小熊系列 苹果手机自拍杆 三级美颜补光灯 苹果华为便携自拍
         * masterPic : http://172.17.8.100/images/small/commodity/sjsm/sjpj/4/1.jpg
         * price : 98
         * saleNum : 0
         */

        private int commodityId;
        private String commodityName;
        private String masterPic;
        private String price;
        private int saleNum;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
    }
}
