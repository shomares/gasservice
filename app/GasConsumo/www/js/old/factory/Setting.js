"use strict";
var Setting = function () {
    this.url = "https://gasservice-emejia.rhcloud.com";
};
Setting.instance = null;

Setting.getInstance = function () {
    if (Setting.instance === null)
        Setting.instance = new Setting();
    return Setting.instance;
};


