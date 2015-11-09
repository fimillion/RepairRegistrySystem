/* jshint forin:true, noarg:true, noempty:true, eqeqeq:true, boss:true, undef:true, curly:true, browser:true, jquery:true */
/*
 * jQuery MultiSelect UI Widget Filtering Plugin 1.5pre
 * Copyright (c) 2012 Eric Hynds
 *
 * http://www.erichynds.com/jquery/jquery-ui-multiselect-widget/
 *
 * Depends:
 *   - jQuery UI MultiSelect widget
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 *
 */
!function(e){var t=/[\-\[\]{}()*+?.,\\\^$|#\s]/g;e.widget("ech.multiselectfilter",{options:{label:"Filter:",width:null,placeholder:"Enter keywords",autoReset:!1},_create:function(){var t=this.options,i=e(this.element),n=this.instance=i.data("echMultiselect")||i.data("multiselect")||i.data("ech-multiselect"),s=(this.header=n.menu.find(".ui-multiselect-header").addClass("ui-multiselect-hasfilter"),this.wrapper=e('<div class="ui-multiselect-filter">'+(t.label.length?t.label:"")+'<input placeholder="'+t.placeholder+'" type="search"'+(/\d/.test(t.width)?'style="width:'+t.width+'px"':"")+" /></div>").prependTo(this.header));this.inputs=n.menu.find('input[type="checkbox"], input[type="radio"]'),this.input=s.find("input").bind({keydown:function(e){13===e.which&&e.preventDefault()},keyup:e.proxy(this._handler,this),click:e.proxy(this._handler,this)}),this.updateCache(),n._toggleChecked=function(t,i){var s=i&&i.length?i:this.labels.find("input"),l=this,h=n._isOpen?":disabled, :hidden":":disabled";s=s.not(h).each(this._toggleState("checked",t)),this.update();var r=s.map(function(){return this.value}).get();this.element.find("option").filter(function(){!this.disabled&&e.inArray(this.value,r)>-1&&l._toggleState("selected",t).call(this)}),s.length&&this.element.trigger("change")};var l=e(document).bind("multiselectrefresh",e.proxy(function(){this.updateCache(),this._handler()},this));this.options.autoReset&&l.bind("multiselectclose",e.proxy(this._reset,this))},_handler:function(i){var n=e.trim(this.input[0].value.toLowerCase()),s=this.rows,l=this.inputs,h=this.cache;if(n){s.hide();var r=new RegExp(n.replace(t,"\\$&"),"gi");this._trigger("filter",i,e.map(h,function(e,t){return-1!==e.search(r)?(s.eq(t).show(),l.get(t)):null}))}else s.show();this.instance.menu.find(".ui-multiselect-optgroup-label").each(function(){var t=e(this),i=t.nextUntil(".ui-multiselect-optgroup-label").filter(function(){return"none"!==e.css(this,"display")}).length;t[i?"show":"hide"]()})},_reset:function(){this.input.val("").trigger("keyup")},updateCache:function(){this.rows=this.instance.menu.find(".ui-multiselect-checkboxes li:not(.ui-multiselect-optgroup-label)"),this.cache=this.element.children().map(function(){var t=e(this);return"optgroup"===this.tagName.toLowerCase()&&(t=t.children()),t.map(function(){return this.innerHTML.toLowerCase()}).get()}).get()},widget:function(){return this.wrapper},destroy:function(){e.Widget.prototype.destroy.call(this),this.input.val("").trigger("keyup"),this.wrapper.remove()}})}(jQuery);