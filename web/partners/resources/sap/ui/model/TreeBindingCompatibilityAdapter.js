/*!
 * UI development toolkit for HTML5 (OpenUI5)
 * (c) Copyright 2009-2016 SAP SE or an SAP affiliate company.
 * Licensed under the Apache License, Version 2.0 - see LICENSE.txt.
 */
sap.ui.define(['jquery.sap.global','sap/ui/model/TreeBinding','sap/ui/model/ClientTreeBinding','sap/ui/table/TreeAutoExpandMode','sap/ui/model/ChangeReason','sap/ui/model/TreeBindingUtils'],function(q,T,C,a,b,c){"use strict";var d=function(B,t){var e=t;q.extend(B,{_init:function(E){this._bExpandFirstLevel=E;this.mContextInfo={};this._initContexts();if(E&&!this._bFirstLevelExpanded){this._expandFirstLevel();}},_initContexts:function(s){this.aContexts=this.getRootContexts(0,Number.MAX_VALUE);for(var i=0,l=this.aContexts.length;i<l;i++){var o=this._getContextInfo(this.aContexts[i]);this._setContextInfo({oContext:this.aContexts[i],iLevel:0,bExpanded:o?o.bExpanded:false});}if(this._bExpandFirstLevel&&!this._bFirstLevelExpanded){this._expandFirstLevel(s);}},_expandFirstLevel:function(s){var e=this;if(this.aContexts&&this.aContexts.length>0){q.each(this.aContexts.slice(),function(i,o){if(!s){e._loadChildContexts(o);}e._getContextInfo(o).bExpanded=true;});this._bFirstLevelExpanded=true;}},_fnFireFilter:B._fireFilter,_fireFilter:function(){this._fnFireFilter.apply(this,arguments);this._initContexts(true);this._restoreContexts(this.aContexts);},_fnFireChange:B._fireChange,_fireChange:function(){this._fnFireChange.apply(this,arguments);this._initContexts(true);this._restoreContexts(this.aContexts);},_restoreContexts:function(f){var e=this;var n=[];q.each(f.slice(),function(i,o){var g=e._getContextInfo(o);if(g&&g.bExpanded){n.push.apply(n,e._loadChildContexts(o));}});if(n.length>0){this._restoreContexts(n);}},_loadChildContexts:function(o){var f=this._getContextInfo(o);var I=q.inArray(o,this.aContexts);var n=this.getNodeContexts(o,0,Number.MAX_VALUE);for(var i=0,l=n.length;i<l;i++){this.aContexts.splice(I+i+1,0,n[i]);var g=this._getContextInfo(n[i]);this._setContextInfo({oParentContext:o,oContext:n[i],iLevel:f.iLevel+1,bExpanded:g?g.bExpanded:false});}return n;},_getContextInfo:function(o){return o?this.mContextInfo[o.getPath()]:undefined;},_setContextInfo:function(D){if(D&&D.oContext){this.mContextInfo[D.oContext.getPath()]=D;}},getLength:function(){return this.aContexts?this.aContexts.length:0;},getContexts:function(s,l){return this.aContexts.slice(s,s+l);},getNodes:function(s,l){var f=this.getContexts(s,s+l);var n=[];for(var i=0;i<f.length;i++){var o=this._getContextInfo(f[i])||{};var g=f[i];n.push({context:g,level:o.iLevel,parent:o.oParentContext,nodeState:{expanded:o.bExpanded,collapsed:!o.bExpanded,selected:false}});}return n;},hasChildren:function(){return true;},nodeHasChildren:function(){return true;},getContextByIndex:function(r){return this.aContexts[r];},getLevel:function(o){var f=this._getContextInfo(o);return f?f.iLevel:-1;},isExpanded:function(r){var o=this.getContextByIndex(r);var f=this._getContextInfo(o);return f?f.bExpanded:false;},expandContext:function(o){var f=this._getContextInfo(o);if(f&&!f.bExpanded){this.storeSelection();this._loadChildContexts(o);f.bExpanded=true;this._fireChange();this.restoreSelection();}},expand:function(r){this.expandContext(this.getContextByIndex(r));},collapseContext:function(o,s){var f=this._getContextInfo(o);if(f&&f.bExpanded){this.storeSelection();for(var i=this.aContexts.length-1;i>0;i--){if(this._getContextInfo(this.aContexts[i]).oParentContext===o){this.aContexts.splice(i,1);}}f.bExpanded=false;if(!s){this._fireChange();}this.restoreSelection();}},collapse:function(r){this.collapseContext(this.getContextByIndex(r));},collapseToLevel:function(l){if(!l||l<0){l=0;}var f=this.aContexts.slice();for(var i=f.length-1;i>=0;i--){var g=this.getLevel(f[i]);if(g!=-1&&g>=l){this.collapseContext(f[i],true);}}this._fireChange();},toggleContext:function(o){var f=this._getContextInfo(o);if(f){if(f.bExpanded){this.collapseContext(o);}else{this.expandContext(o);}}},toggleIndex:function(r){this.toggleContext(this.getContextByIndex(r));},storeSelection:function(){var s=e.getSelectedIndices();var S=[];q.each(s,function(i,v){S.push(e.getContextByIndex(v));});this._aSelectedContexts=S;},restoreSelection:function(){e.clearSelection();var _=this._aSelectedContexts;q.each(this.aContexts,function(i,o){if(q.inArray(o,_)>=0){e.addSelectionInterval(i,i);}});this._aSelectedContexts=undefined;},attachSelectionChanged:function(){return undefined;},clearSelection:function(){e._oSelection.clearSelection();},attachSort:function(){},detachSort:function(){}});B._init(t.getExpandFirstLevel());};return d;},true);
