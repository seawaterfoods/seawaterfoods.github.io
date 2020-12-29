---
title: Hexo+GitHub，快速建立靜態Blog並且帶著到處趴趴走。
date: 2020-12-29 11:56:07
category: Hexo
tags: [Hexo]
index_img: /img/hexo-cover.png
---
![](/seawaterfoods/img/hexo-cover.png)
### 前言
> 在剛轉職當工程師時一直想建立Blog來記錄學習的點點滴滴，但老實說對於如何設計畫面實在是沒有美感，而且還有維護更新等等問題，所以乾脆去找了許多可以快速建立Blog方法、框架，最後選擇快速又方便的Hexo+GitHub，
> 但Hexo內建的發布功能僅能單純上傳靜態網頁部分，為了方便在不同電腦更新自己Blog，在這裡分享如何設定與基本安裝方法。
<!-- more -->
### 安裝與建置
> 由於Hexo是基於Node.js開發的Blog框架，沒安裝Node.js(Node.js 版本需不低於8.10，建議使用 Node.js 10.0 及以上版本)
> 可以直接去[NodeJS官方網站](https://nodejs.org/en/, "NodeJS")下載一個LTS的版本就可以了。
<br/>
> ![](/seawaterfoods/img/nodejs.png)
<br/>
> 當然還需要有安裝[Git](https://git-scm.com/,"Git")。
> 安裝好兩個必要軟體後就可以直接使用NPM來安裝Hexo。

**1. 安裝 Hexo**
```
npm install hexo-cli -g
```
如果安裝完成便可以直接輸入指令來查詢Hexo版本
```
hexo version
```
**2. 初始化Blog**
```　
hexo init yourname.github.io        # 初始化 blog，由於要使用到GitHub Page，所以建議命名'你的githubID'.github.io
cd yourname.github.io               # 移動到剛創建的 blog 資料夾裡
npm install                         # 安裝相關套件
```
![](/seawaterfoods/img/hexoinit.png)
**3. 設定_config.yml**
<p>
為了可以在不同電腦更新Blog所以不去使用Hexo內建的deploy功能，
但為了要顯示靜態網頁部分需要額外設定一些參數，使github可以抓到靜態網頁。
</p>

![](/seawaterfoods/img/hexo-myconfig.png)

**4. 設置跳轉頁面**
<p>
在blog資料夾中建立index.html，設置跳轉到首頁的html。<br/>
也可以順便建立Blog的404頁面。
</p>

```
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="refresh" content="0;url=/yourname/" />
  </head>
</html>
```
![](/seawaterfoods/img/returnindex.png)
**5. Hexo Blog上傳GitHub**
<p>
建立Repositories。
</p>

![](/seawaterfoods/img/githubpage.png)
<br/>
直接上傳到github。
<br/>
**PS:需配置好認證祕鑰–保證本地和遠端建立安全連線。**

```
git init                                                                #建立數據庫
git add *                                                               #把資料夾內所有檔案列為tracked對象
git commit -m 'hexo'                                                    #提交版本
git remote add origin git@github.com:yourname/yourname.github.io.git    #設定遠端節點
git push -u origin master                                               #把本地端(master)Push到遠端(origin)
```
或是使用各種GUI界面來管理版本控制內容的軟體來Push到GitHub上。

### 在其他電腦更新Hexo Blog
<p>
在其他電腦下載好自己的Hexo Blog專案後，在專案資料夾直接安裝npm套件即可使用hexo。
</p>

```
npm install                         # 安裝相關套件
```

### 結論
<p>
Hexo內建的發布功能只會將靜態網頁資料夾(public)上傳到github上，但為了在不同電腦中都可以上傳Blog所以稍微研究一下(汗)；<br/>
其他Hexo操作像是建立新頁面之類的方法，可以參考官方網站及其他Hexo教學文，這裡把URL放在下方參考資料中。
</p>

### 參考資料
>[NodeJS官方網站](https://nodejs.org/en/, "NodeJS")<br/>
[Hexo+GitHub，新手也可以快速建立部落格](https://blackmaple.me/hexo-tutorial/, "blackmaple")<br/>
[Markdown文件(.md寫法)](https://markdown.tw/#autoescape, "Markdown")<br/>
[hexo系列-00 hexo簡介＆環境安裝](https://augustushsu.github.io/2019/12/09/hexo-00/, "augustushsu")