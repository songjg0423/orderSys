/////////////////////////////////////////////////////////////////
// 菜单相关。
// 
////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////
// global vars defination

var FRAME_businessfrm = "businessfrm";
var Transaction_Logout = "logout.do";


////////////////////////////////////////////////////////////////

// menu clss.
function Menu(name){
    this.name = name != null ? name : "DefaultMenu";
    Menu.menuMap[this.name] = this;
    
    this.rootItems = new Array();
    this.imageList = new ImageList();
    this.itemMap = new Object();
    this.clickAction = Menu.defaultClickAction;
    this.hoverAction = Menu.defaultHoverAction;
    
    this.setImages = Menu_setImages;
    this.get = Menu_get;
    this.contains = Menu_contains;
    this.add = Menu_add;
    this.render = Menu_render;
    this.show = Menu_show;
    this.setClickAction = Menu_setClickAction;
    this.setHoverAction = Menu_setHoverAction;
}

Menu.Leaf = "Leaf";
Menu.Opened = "Opened";
Menu.Closed = "Closed";

Menu.menuMap = new Object();
Menu.get = _Menu_get;
Menu.click = _Menu_click;
Menu.hover = _Menu_hover;
Menu.defaultClickAction = _Menu_defaultClickAction;
Menu.defaultHoverAction = _Menu_defaultHoverAction;
Menu.showPage = Menu_defaultShowPage;



function _Menu_get(name){
    return Menu.menuMap[name];
}

function _Menu_click(menu, item){
    var oMenu = Menu.get(menu);
    if (oMenu != null) {
        var oItem = oMenu.get(item);
        oMenu.clickAction(oItem);
    }
}

function _Menu_hover(menu, item, flag){
    var oMenu = Menu.get(menu);
    if (oMenu != null) {
        var oItem = oMenu.get(item);
        oMenu.hoverAction(oItem, flag);
    }
}

function Menu_defaultShowPage(urlstr){
    if (urlstr == Transaction_Logout) {
        parent.location = urlstr;
        return;
    }
    parent.frames[FRAME_businessfrm].location = urlstr;
}

function _Menu_defaultClickAction(item){
    if (item.disabled) {
        return;
    }
    if (item.children.length > 0) {
        if (item.collapsed) {
            var sibling = findExpandedSibling(item);
            if (sibling != null) {
                recursiveCollapse(sibling);
            }
            item.expand();
        }
        else {
            recursiveCollapse(item);
        }
    }
    else {
        if (!item.notLeaf) {
            Menu.showPage(item.data);
        }
    }
}

function recursiveCollapse(item){
    if (item.children.length > 0) {
        item.collapse();
        for (var i = 0; i < item.children.length; i++) {
            recursiveCollapse(item.children[i]);
        }
    }
}

function findExpandedSibling(item){
    var items = item.parent == null ? item.menu.rootItems : item.parent.children;
    for (var i = 0; i < items.length; i++) {
        var sibling = items[i];
        if (sibling != item && !sibling.collapsed) 
            return sibling;
    }
    return null;
}

function _Menu_defaultHoverAction(item, flag){
    if (item.disabled) 
        return;
    
    var lbl = eval("lbl_" + item.name);
    lbl.style.color = flag ? "red" : "";
}

function Menu_setImages(prefix, suffix, leafUrl, openedUrl, closedUrl){
    this.imageList.setUrlPrefix(prefix);
    this.imageList.setUrlSuffix(suffix);
    this.imageList.add(Menu.Leaf, leafUrl);
    this.imageList.add(Menu.Opened, openedUrl);
    this.imageList.add(Menu.Closed, closedUrl);
}

function Menu_get(name){
    return this.itemMap[name];
}

function Menu_contains(name){
    return typeof(this.itemMap[name]) != "undefined";
}

function Menu_add(parentVar, name, caption, data, notLeaf){

    var parent = parentVar != "root" ? parentVar : null;
    var item = new MenuItem(this, name, caption, data, notLeaf);
    this.itemMap[name] = item;
    if (parent != null) {
        this.get(parent).add(item);
    }
    else {
        this.rootItems[this.rootItems.length] = item;
    }
    return item;
}

function Menu_render(){
    var result = "";
    for (var i = 0; i < this.rootItems.length; i++) {
        result += this.rootItems[i].render();
    }
    return result;
}

function Menu_show(div){
    div.innerHTML = this.render();
}

function Menu_setClickAction(action){
    this.clickAction = action != null ? action : Menu.defaultClickAction;
}

function Menu_setHoverAction(action){
    this.hoverAction = action != null ? action : Menu.defaultHoverAction;
}

function MenuItem(menu, name, caption, data, notLeaf){
    this.menu = menu;
    this.name = name;
    this.caption = caption != null ? caption : "";
    this.data = data;
    this.notLeaf = notLeaf != null ? notLeaf : false;
    this.parent = null;
    this.children = new Array();
    this.collapsed = true;
    this.enabled = true;
    this.visible = true;
    
    this.setCaption = MenuItem_setCaption;
    this.getCaption = MenuItem_getCaption;
    this.add = MenuItem_add;
    this.collapse = MenuItem_collapse;
    this.expand = MenuItem_expand;
    this.renderIcon = MenuItem_renderIcon;
    this.renderCaption = MenuItem_renderCaption;
    this.renderSpan = MenuItem_renderSpan;
    this.renderChildren = MenuItem_renderChildren;
    this.render = MenuItem_render;
    this.getActionScript = MenuItem_getActionScript;
    this.getHoverScript = MenuItem_getHoverScript;
    this.isAncestorOf = MenuItem_isAncestorOf;
    this.enable = MenuItem_enable;
    this.disable = MenuItem_disable;
    this.hide = MenuItem_hide;
    this.show = MenuItem_show;
}

function MenuItem_setCaption(caption){
    this.caption = caption != null ? caption : "";
}

function MenuItem_getCaption(){
    return caption;
}

function MenuItem_add(child){
    child.parent = this;
    this.children[this.children.length] = child;
}

function MenuItem_collapse(){
    var sub = eval("sub_" + this.name);
    sub.style.display = "none";
    var img = eval("img_" + this.name);
    img.src = this.menu.imageList.getUrl(Menu.Closed);
    this.collapsed = true;
}

function MenuItem_expand(){
    var sub = eval("sub_" + this.name);
    sub.style.display = "block";
    var img = eval("img_" + this.name);
    img.src = this.menu.imageList.getUrl(Menu.Opened);
    this.collapsed = false;
}

function MenuItem_renderIcon(){
    var css = new CssStyles();
    css.add("border-style", "none");
    css.add("margin-right", "2px");
    
    var attr = new HtmlAttributes();
    attr.add("id", "img_" + this.name);
    
    var icon = Menu.Leaf;
    if (this.notLeaf || this.children.length > 0) {
        icon = this.collapsed ? Menu.Closed : Menu.Opened;
    }
    return this.menu.imageList.render(icon, attr, css) + "\r\n";
}

function MenuItem_renderCaption(){
    var attr = new HtmlAttributes();
    attr.add("id", "lbl_" + this.name);
    return "<label " + attr.render() + ">" + this.caption + "</label>\r\n";
}

function MenuItem_renderSpan(){
    var css = new CssStyles();
    css.add("cursor", "hand");
    if (this.notLeaf || this.children.length > 0) {
        css.add("font-weight", "bold");
    }
    
    var attr = new HtmlAttributes();
    attr.add("onclick", this.getActionScript());
    attr.add("onmouseover", this.getHoverScript(true));
    attr.add("onmouseout", this.getHoverScript(false));
    attr.addStyles(css);
    
    var result = "";
    result += "<span " + attr.render() + ">\r\n";
    result += this.renderIcon();
    result += this.renderCaption();
    result += "</span>\r\n";
    return result;
}

function MenuItem_renderChildren(){
    var result = "";
    for (var i = 0; i < this.children.length; i++) {
        result += this.children[i].render();
    }
    return result;
}

function MenuItem_render(){
    var css = new CssStyles();
    css.add("font-size", "9pt");
    if (!this.visible) {
        css.add("display", "none");
    }
    
    var attr = new HtmlAttributes();
    attr.add("id", this.name);
    attr.add("border", "0");
    attr.add("cellspacing", "0");
    attr.add("cellpadding", "1");
    if (this.disabled) {
        attr.add("disabled");
    }
    attr.addStyles(css);
    
    var result = "";
    result += "<table " + attr.render() + ">\r\n";
    result += "<tr><td>\r\n";
    result += this.renderSpan();
    result += "</td></tr>\r\n";
    if (this.children.length > 0) {
        var tdcss = new CssStyles();
        tdcss.add("padding-left", "12px");
        tdcss.add("display", "none");
        
        var tdattr = new HtmlAttributes();
        tdattr.add("id", "sub_" + this.name);
        tdattr.addStyles(tdcss);
        
        result += "<tr><td " + tdattr.render() + ">\r\n";
        result += this.renderChildren();
        result += "</td></tr>\r\n";
    }
    result += "</table>\r\n";
    return result;
}

function MenuItem_getActionScript(){
    return "Menu.click(\"" + this.menu.name + "\",\"" + this.name + "\")";
}

function MenuItem_getHoverScript(flag){
    return "Menu.hover(\"" + this.menu.name + "\",\"" + this.name + "\", " + flag + ")";
}

function MenuItem_isAncestorOf(item){
    var i = item;
    while (i != null) {
        if (i == this) 
            return true;
        i = i.parent;
    }
    return false;
}

function MenuItem_enable(){
    this.disabled = false;
    var node = eval(this.name);
    node.disabled = false;
}

function MenuItem_disable(){
    this.disabled = true;
    if (!this.collapsed) {
        this.collapse();
    }
    var node = eval(this.name);
    node.disabled = true;
}

function MenuItem_hide(){
    this.visible = false;
    if (!this.collapsed) {
        this.collapse();
    }
    var node = eval(this.name);
    node.style.display = "none";
}

function MenuItem_show(){
    this.visible = true;
    var node = eval(this.name);
    node.style.display = "block";
}


function ImageList(){
    this.urlPrefix = "";
    this.urlSuffix = "";
    this.images = new Object();
    this.defaultAttributes = null;
    this.defaultStyles = null;
    
    this.add = ImageList_add;
    this.remove = ImageList_remove;
    this.contains = ImageList_contains;
    this.render = ImageList_render;
    this.setUrlPrefix = ImageList_setUrlPrefix;
    this.setUrlSuffix = ImageList_setUrlSuffix;
    this.setDefaultAttributes = ImageList_setDefaultAttributes;
    this.setDefaultStyles = ImageList_setDefaultStyles;
    this.load = ImageList_load;
    this.loadAll = ImageList_loadAll;
    this.getUrl = ImageList_getUrl;
    this.getImage = ImageList_getImage;
}

function ImageList_add(name, urlText){
    var placeholder = new Object();
    placeholder.urlText = urlText;
    this.images[name] = placeholder;
}

function ImageList_remove(name){
    delete this.images[name];
}

function ImageList_contains(name){
    return typeof(this.images[name]) != "undefined";
}

function ImageList_render(name, attributes, styles){
    var css = new CssStyles();
    if (this.defaultStyles != null) {
        css.merge(this.defaultStyles);
    }
    if (styles != null) {
        css.merge(styles);
    }
    
    var attr = new HtmlAttributes();
    if (this.defaultAttributes != null) {
        attr.merge(this.defaultAttributes);
    }
    if (attributes != null) {
        attr.merge(attributes);
    }
    
    attr.addStyles(css);
    attr.add("src", this.getUrl(name));
    return "<img " + attr.render() + ">";
}

function ImageList_setUrlPrefix(prefix){
    this.urlPrefix = prefix != null ? prefix : "";
}

function ImageList_setUrlSuffix(suffix){
    this.urlSuffix = suffix != null ? suffix : "";
}

function ImageList_setDefaultAttributes(attributes){
    this.defaultAttributes = null;
    if (attributes != null) {
        this.defaultAttributes = new HtmlAttributes();
        this.defaultAttributes.merge(attributes);
    }
}

function ImageList_setDefaultStyles(styles){
    this.defaultStyles = null;
    if (styles != null) {
        this.defaultStyles = new CssStyles();
        this.defaultStyles.merge(styles);
    }
}

function ImageList_load(name){
    var img = new Image();
    img.src = this.getUrl(name);
    this.images[name].img = img;
    return img;
}

function ImageList_loadAll(){
    for (var name in this.images) {
        this.load(name);
    }
}

function ImageList_getUrl(name){
    return this.urlPrefix + this.images[name].urlText + this.urlSuffix;
}

function ImageList_getImage(name){
    if (!this.contains(name)) 
        return null;
    var img = this.images[name].img;
    return img != null ? img : load(name);
}

function CssStyles(){
    this.styleMap = new Object();
    
    this.add = CssStyles_add;
    this.remove = CssStyles_remove;
    this.contains = CssStyles_contains;
    this.render = CssStyles_render;
    this.escape = CssStyles_escape;
    this.merge = CssStyles_merge;
}

function CssStyles_add(name, value){
    this.styleMap[name] = value;
}

function CssStyles_remove(name){
    delete this.styleMap[name];
}

function CssStyles_contains(name){
    return typeof(this.styleMap[name]) != "undefined";
}

function CssStyles_render(){
    var result = "";
    for (var name in this.styleMap) {
        result += name;
        
        var value = this.styleMap[name];
        if (value != null) {
            result += ":" + this.escape(value) + ";";
        }
        
        result += " ";
    }
    return result;
}

function CssStyles_escape(text){
    return text.replace(/"/g, "\'");
}

function CssStyles_merge(styles){
    for (var name in styles.styleMap) {
        var value = styles.styleMap[name];
        this.add(name, value);
    }
}

function HtmlAttributes(){
    this.attributeMap = new Object();
    
    this.add = HtmlAttributes_add;
    this.remove = HtmlAttributes_remove;
    this.contains = HtmlAttributes_contains;
    this.addStyles = HtmlAttributes_addStyles;
    this.render = HtmlAttributes_render;
    this.escape = HtmlAttributes_escape;
    this.merge = HtmlAttributes_merge;
}

function HtmlAttributes_add(name, value){
    this.attributeMap[name] = value;
}

function HtmlAttributes_remove(name){
    delete this.attributeMap[name];
}

function HtmlAttributes_contains(name){
    return typeof(this.attributeMap[name]) != "undefined";
}

function HtmlAttributes_addStyles(styles){
    var css = styles.render();
    if (css.length > 0) {
        this.add("style", css);
    }
}

function HtmlAttributes_render(){
    var result = "";
    for (var name in this.attributeMap) {
        result += name;
        
        var value = this.attributeMap[name];
        if (value != null) {
            result += "=\"" + this.escape(value) + "\"";
        }
        
        result += " ";
    }
    return result;
}

function HtmlAttributes_escape(text){
    return text.replace(/"/g, "\'");
}

function HtmlAttributes_merge(attributes){
    for (var name in attributes.attributeMap) {
        var value = attributes.attributeMap[name];
        this.add(name, value);
    }
}
