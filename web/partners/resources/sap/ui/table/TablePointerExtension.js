/*!
 * UI development toolkit for HTML5 (OpenUI5)
 * (c) Copyright 2009-2016 SAP SE or an SAP affiliate company.
 * Licensed under the Apache License, Version 2.0 - see LICENSE.txt.
 */
sap.ui.define(['jquery.sap.global','./TableExtension','./TableUtils','sap/ui/Device'],function(q,T,a,D){"use strict";var E={_getEventPosition:function(e,t){var p;if(t._isTouchMode(e)){p=e.targetTouches?e.targetTouches[0]:e.originalEvent.targetTouches[0];}else{p=e;}return{x:p.pageX,y:p.pageY};}};var C={initColumnResizing:function(t,e){if(t._bIsColumnResizerMoving){return;}t._bIsColumnResizerMoving=true;t.$().toggleClass("sapUiTableResizing",true);var d=q(document),f=t._isTouchMode(e);t._$colResize=t.$("rsz");t._iColumnResizeStart=E._getEventPosition(e,t).x;d.bind((f?"touchend":"mouseup")+".sapUiTableColumnResize",C.exitColumnResizing.bind(t));d.bind((f?"touchmove":"mousemove")+".sapUiTableColumnResize",C.onMouseMoveWhileColumnResizing.bind(t));t._disableTextSelection();},exitColumnResizing:function(e){C._resizeColumn(this,this._iLastHoveredColumnIndex);},onMouseMoveWhileColumnResizing:function(e){var l=E._getEventPosition(e,this).x;if(this._iColumnResizeStart&&l<this._iColumnResizeStart+3&&l>this._iColumnResizeStart-3){return;}if(this._isTouchMode(e)){e.stopPropagation();e.preventDefault();}this._$colResize.toggleClass("sapUiTableColRszActive",true);var o=this._getVisibleColumns()[this._iLastHoveredColumnIndex];var d=l-this._iColumnResizeStart;var w=Math.max(o.$().width()+d*(this._bRtlMode?-1:1),this._iColMinWidth);var r=this.$().find(".sapUiTableCnt").offset().left;var i=Math.floor((l-r)-(this._$colResize.width()/2));this._$colResize.css("left",i+"px");o._iNewWidth=w;},_cleanupColumResizing:function(t){if(t._$colResize){t._$colResize.toggleClass("sapUiTableColRszActive",false);t._$colResize=null;}t._iColumnResizeStart=null;t._bIsColumnResizerMoving=false;t.$().toggleClass("sapUiTableResizing",false);t._enableTextSelection();var d=q(document);d.unbind("touchmove.sapUiTableColumnResize");d.unbind("touchend.sapUiTableColumnResize");d.unbind("mousemove.sapUiTableColumnResize");d.unbind("mouseup.sapUiTableColumnResize");},_resizeColumn:function(t,i){var v=t._getVisibleColumns(),o,r=false;if(i>=0&&i<v.length){o=v[i];if(o._iNewWidth){var w;var A=t.$().find(".sapUiTableCtrl").width();if(!t._checkPercentageColumnWidth()){w=o._iNewWidth+"px";}else{var d=Math.round(100/A*o._iNewWidth);w=d+"%";}if(t._updateColumnWidth(o,w,true)){t._resizeDependentColumns(o,w);}delete o._iNewWidth;r=true;}}C._cleanupColumResizing(t);o.focus();if(r){t.invalidate();}},doAutoResizeColumn:function(t,i){var v=t._getVisibleColumns(),o;if(i>=0&&i<v.length){o=v[i];if(!o.getAutoResizable()||!o.getResizable()){return;}var n=C._calculateAutomaticColumnWidth.apply(t,[o,i]);if(n){o._iNewWidth=n;C._resizeColumn(t,i);}}},_calculateAutomaticColumnWidth:function(o,d){function e(r){var t=["sap/m/Text","sap/m/Label","sap/m/Link","sap/m/Input","sap/ui/commons/TextView","sap/ui/commons/Label","sap/ui/commons/Link","sap/ui/commons/TextField"];var k=false;for(var i=0;i<t.length;i++){k=k||a.isInstanceOf(r,t[i]);}if(!k&&typeof c._fnCheckTextBasedControl==="function"&&c._fnCheckTextBasedControl(r)){k=true;}return k;}var $=this.$();var h=0;var f=$.find('td[headers=\"'+this.getId()+'_col'+d+'\"]').children("div");var H=o.getHeaderSpan();var g=o.getLabel();var j=o.getTemplate();var k=e(j);var l=document.createElement("div");document.body.appendChild(l);q(l).addClass("sapUiTableHiddenSizeDetector");var m=o.getMultiLabels();if(m.length==0&&!!g){m=[g];}if(m.length>0){q.each(m,function(r,L){var s;if(!!L.getText()){q(l).text(L.getText());h=l.scrollWidth;}else{h=L.$().scrollWidth;}h=h+$.find("#"+o.getId()+"-icons").first().width();$.find(".sapUiTableColIcons#"+o.getId()+"_"+r+"-icons").first().width();if(H instanceof Array&&H[r]>1){s=H[r];}else if(H>1){s=H;}if(!!s){var i=s-1;while(i>d){h=h-(this._getVisibleColumns()[d+i].$().width()||0);i-=1;}}});}var n=Math.max.apply(null,f.map(function(){var _=q(this);return parseInt(_.css('padding-left'),10)+parseInt(_.css('padding-right'),10)+parseInt(_.css('margin-left'),10)+parseInt(_.css('margin-right'),10);}).get());var p=Math.max.apply(null,f.children().map(function(){var w=0,W=0;var _=q(this);var s=_.text()||_.val();if(k){q(l).text(s);W=l.scrollWidth;}else{W=this.scrollWidth;}if(h>W){W=h;}w=W+parseInt(_.css('margin-left'),10)+parseInt(_.css('margin-right'),10)+n+1;return w;}).get());q(l).remove();return Math.max(p,this._iColMinWidth);},initColumnTracking:function(t){t.$().find(".sapUiTableCtrlScr, .sapUiTableCtrlScrFixed, .sapUiTableColHdrScr, .sapUiTableColHdrFixed").mousemove(function(e){var d=this.getDomRef();if(!d||this._bIsColumnResizerMoving){return;}var p=e.clientX,f=d.getBoundingClientRect(),l=0,r=this._bRtlMode?10000:-10000;for(var i=0;i<this._aTableHeaders.length;i++){var o=this._aTableHeaders[i].getBoundingClientRect();if(this._bRtlMode){if(p<o.right-5){l=i;r=o.left-f.left;}}else{if(p>o.left+5){l=i;r=o.right-f.left;}}}var g=this._getVisibleColumns()[l];if(g&&g.getResizable()){this.$("rsz").css("left",r+"px");this._iLastHoveredColumnIndex=l;}}.bind(t));}};var I={initInteractiveResizing:function(t,e){var B=q(document.body),s=t.$("sb"),d=q(document),o=s.offset(),h=s.height(),w=s.width(),f=t._isTouchMode(e);B.bind("selectstart",I.onSelectStartWhileInteractiveResizing);B.append("<div id=\""+t.getId()+"-ghost\" class=\"sapUiTableInteractiveResizerGhost\" style =\" height:"+h+"px; width:"+w+"px; left:"+o.left+"px; top:"+o.top+"px\" ></div>");s.append("<div id=\""+t.getId()+"-rzoverlay\" style =\"left: 0px; right: 0px; bottom: 0px; top: 0px; position:absolute\" ></div>");d.bind((f?"touchend":"mouseup")+".sapUiTableInteractiveResize",I.exitInteractiveResizing.bind(t));d.bind((f?"touchmove":"mousemove")+".sapUiTableInteractiveResize",I.onMouseMoveWhileInteractiveResizing.bind(t));t._disableTextSelection();},exitInteractiveResizing:function(e){var B=q(document.body),d=q(document),t=this.$(),g=this.$("ghost"),l=E._getEventPosition(e,this).y;var n=l-t.find(".sapUiTableCCnt").offset().top-g.height()-t.find(".sapUiTableFtr").height();this._setRowContentHeight(n);this._adjustRows(this._calculateRowsToDisplay(n));g.remove();this.$("rzoverlay").remove();B.unbind("selectstart",I.onSelectStartWhileInteractiveResizing);d.unbind("touchend.sapUiTableInteractiveResize");d.unbind("touchmove.sapUiTableInteractiveResize");d.unbind("mouseup.sapUiTableInteractiveResize");d.unbind("mousemove.sapUiTableInteractiveResize");this._enableTextSelection();},onSelectStartWhileInteractiveResizing:function(e){e.preventDefault();e.stopPropagation();return false;},onMouseMoveWhileInteractiveResizing:function(e){var l=E._getEventPosition(e,this).y;var m=this.$().offset().top;if(l>m){this.$("ghost").css("top",l+"px");}}};var R={initReordering:function(t,i,e){var d=t._isTouchMode(e),o=t.getColumns()[i],$=o.$(),f=t.$();t._disableTextSelection();f.addClass("sapUiTableDragDrop");t._$ColGhost=$.clone().removeAttr("id");$.css({"opacity":".25"});t._$ColGhost.addClass("sapUiTableColGhost").css({"left":-10000,"top":-10000,"position":"absolute","z-index":t.$().zIndex()+10});f.find(".sapUiTableCol").each(function(h,j){var _=q(this),p=this.getBoundingClientRect(),w=_.outerWidth();_.css({position:"relative"});_.data("pos",{left:p.left,center:p.left+w/2,right:p.left+w});});t._$ColGhost.appendTo(document.body);var g=q(document);g.bind((d?"touchend":"mouseup")+".sapUiColumnMove",R.exitReordering.bind(t));g.bind((d?"touchmove":"mousemove")+".sapUiColumnMove",R.onMouseMoveWhileReordering.bind(t));},onMouseMoveWhileReordering:function(e){var t=this.$();var l=E._getEventPosition(e,this);var L=l.x;var i=l.y;var r=this._bRtlMode;var d=parseInt(this._$ColGhost.attr("data-sap-ui-colindex"),10);var $=this.getColumns()[d].$();var o=this._iNewColPos;this._iNewColPos=d;var f=this;t.find(".sapUiTableCol").each(function(g,h){var j=q(h);var k=parseInt(j.attr("data-sap-ui-colindex"),10);var s=R.getHeaderSpan(sap.ui.getCore().byId(j.attr("data-sap-ui-colid")));if(j.get(0)!==$.get(0)){var p=j.data("pos");var B=L>=p.left&&L<=p.center;var A=L>=p.center&&L<=p.right;if(!r&&B||r&&A){f._iNewColPos=k;}else if(!r&&A||r&&B){f._iNewColPos=k+s;}else{f._iNewColPos=f._iNewColPos;}if((B||A)&&k>d){f._iNewColPos--;}}});if(!R.isColumnReorderable(this,this._iNewColPos)){this._iNewColPos=o;}R.animateColumnMove(this,d,o,this._iNewColPos);this._$ColGhost.css({"left":L+5,"top":i+5});},exitReordering:function(e){var t=this;this.$().removeClass("sapUiTableDragDrop");var d=parseInt(this._$ColGhost.attr("data-sap-ui-colindex"),10);var o=this.getColumns()[d];var $=q(document);$.unbind("touchmove.sapUiColumnMove");$.unbind("touchend.sapUiColumnMove");$.unbind("mousemove.sapUiColumnMove");$.unbind("mouseup.sapUiColumnMove");this._$ColGhost.remove();this._$ColGhost=undefined;this._enableTextSelection();var f=this.fireColumnMove({column:o,newPos:this._iNewColPos});var m=d<this._iNewColPos;if(f&&this._iNewColPos!==undefined&&this._iNewColPos!==d){this.removeColumn(o);this.insertColumn(o,this._iNewColPos);var s=R.getHeaderSpan(o);if(s>1){if(!m){this._iNewColPos++;}for(var i=1;i<s;i++){var g=this.getColumns()[m?d:d+i];this.removeColumn(g);this.insertColumn(g,this._iNewColPos);this.fireColumnMove({column:g,newPos:this._iNewColPos});if(!m){this._iNewColPos++;}}}}else{R.animateColumnMove(this,d,this._iNewColPos,d);o.$().css({"backgroundColor":"","backgroundImage":"","opacity":""});}if(this._mTimeouts.reApplyFocusTimer){window.clearTimeout(this._mTimeouts.reApplyFocusTimer);}this._mTimeouts.reApplyFocusTimer=window.setTimeout(function(){var O=a.getFocusedItemInfo(t).cell;a.focusItem(t,0,e);a.focusItem(t,O,e);},0);delete this._iNewColPos;if(this.updateAnalyticalInfo){this.updateAnalyticalInfo(true,true);}},animateColumnMove:function(t,d,o,n){var r=t._bRtlMode;var $=t.getColumns()[d].$();if(o!==n){for(var i=Math.min(o,n),l=Math.max(o,n);i<=l;i++){var e=t.getColumns()[i];if(i!==d&&e.getVisible()){e.$().stop(true,true).animate({left:"0px"});}}var O=0;if(n<d){for(var i=n;i<d;i++){var e=t.getColumns()[i];if(e.getVisible()){var f=e.$();O-=f.outerWidth();f.stop(true,true).animate({left:$.outerWidth()*(r?-1:1)+"px"});}}}else{for(var i=d+1,l=n+1;i<l;i++){var e=t.getColumns()[i];if(e.getVisible()){var f=e.$();O+=f.outerWidth();f.stop(true,true).animate({left:$.outerWidth()*(r?1:-1)+"px"});}}}$.stop(true,true).animate({left:O*(r?-1:1)+"px"});}},getHeaderSpan:function(o){var h=o.getHeaderSpan(),s=1;if(h){s=q.isArray(h)?h[0]:h;}return s;},isColumnReorderable:function(t,i){if(i<t.getFixedColumnCount()||i<t._iFirstReorderableIndex){return false;}return true;}};var b={onmousedown:function(e){this._getKeyboardExtension().initItemNavigation();if(e.button===0){var t=q(e.target);if(e.target===this.getDomRef("sb")){I.initInteractiveResizing(this,e);}else if(e.target===this.getDomRef("rsz")){C.initColumnResizing(this,e);}else if(t.hasClass("sapUiTableColResizer")){var i=t.closest(".sapUiTableCol").attr("data-sap-ui-colindex");this._iLastHoveredColumnIndex=parseInt(i,10);C.initColumnResizing(this,e);}else{var $=t.closest(".sapUiTableCol",this.getDomRef());if($.length===1){var d=parseInt($.attr("data-sap-ui-colindex"),10),o=this.getColumns()[d];var m=o.getAggregation("menu");o._bSkipOpen=m&&m.bOpen;this._bShowMenu=true;this._mTimeouts.delayedMenuTimer=q.sap.delayedCall(200,this,function(){this._bShowMenu=false;});if(this.getEnableColumnReordering()&&!(this._isTouchMode(e)&&t.hasClass("sapUiTableColDropDown"))){this._getPointerExtension().doReorderColumn(d,e);}}}if((D.browser.firefox&&!!(e.metaKey||e.ctrlKey))||t.closest(".sapUiTableHSb",this.getDomRef()).length===1||t.closest(".sapUiTableVSb",this.getDomRef()).length===1){e.preventDefault();}}},onmouseup:function(e){q.sap.clearDelayedCall(this._mTimeouts.delayedColumnReorderTimer);},ondblclick:function(e){if(D.system.desktop&&e.target===this.getDomRef("rsz")){e.preventDefault();C.doAutoResizeColumn(this,this._iLastHoveredColumnIndex);}},onclick:function(e){q.sap.clearDelayedCall(this._mTimeouts.delayedColumnReorderTimer);if(e.isMarked()){return;}var t=q(e.target);if(t.hasClass("sapUiAnalyticalTableSum")){e.preventDefault();return;}else if(t.hasClass("sapUiTableGroupMenuButton")){this._onContextMenu(e);e.preventDefault();return;}else if(t.hasClass("sapUiTableGroupIcon")||t.hasClass("sapUiTableTreeIcon")){if(a.toggleGroupHeader(this,e.target)){return;}}if(!this._findAndfireCellEvent(this.fireCellClick,e)){this._onSelect(e);}else{e.preventDefault();}}};var c=T.extend("sap.ui.table.TablePointerExtension",{_init:function(t,s,S){this._type=s;this._delegate=b;t.addEventDelegate(this._delegate,t);t._iLastHoveredColumnIndex=0;t._bIsColumnResizerMoving=false;t._iFirstReorderableIndex=s==T.TABLETYPES.TREE?1:0;return"PointerExtension";},_attachEvents:function(){var t=this.getTable();if(t){C.initColumnTracking(t);}},_detachEvents:function(){var t=this.getTable();if(t){t.$().find(".sapUiTableCtrlScr, .sapUiTableCtrlScrFixed, .sapUiTableColHdrScr, .sapUiTableColHdrFixed").unbind();}},_debug:function(){this._ExtensionHelper=E;this._ColumnResizeHelper=C;this._InteractiveResizeHelper=I;this._ReorderHelper=R;this._ExtensionDelegate=b;},doAutoResizeColumn:function(i){var t=this.getTable();if(t){C.doAutoResizeColumn(t,i);}},doReorderColumn:function(i,e){var t=this.getTable();if(t&&t.getEnableColumnReordering()&&R.isColumnReorderable(t,i)){t._mTimeouts.delayedColumnReorderTimer=q.sap.delayedCall(200,t,function(){R.initReordering(t,i,e);});}},destroy:function(){var t=this.getTable();if(t){t.removeEventDelegate(this._delegate);}this._delegate=null;T.prototype.destroy.apply(this,arguments);}});return c;},true);
