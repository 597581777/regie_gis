/**
  * @description UI component
  * 基于jquery ui框架开发的ui插件
  * @version 5.0.5
  */
(function($){
    $.widget("csiui.password" , {
		options:{
			verdects:	["弱","中等","强"],
			hintText:   ["太短","太长","不安全"],
			colors: 	["#c06", "#f60","#3c0"],
			common:		["password","sex","god","123456","123","1","123321","asdf","a"],
			minLength:	6,
			maxLength:  12,
			hint:false
		},
		//根据密码校验结果,显示提示信息
		_runPassword: function (password, infoarea, options){
			// Check password
			score = this._checkPassword(password, options);
			// Get controls
	    	var ctlBar = "#" + infoarea + "_bar"; 
	    	var ctlText = "#" + infoarea + "_text";		
			// 参数错误?
			if (score == -1) {
				//max less than min
			}
			//密码太短
			else if (score == -2) {
				strColor = '#ccc';
				strText = options.hintText[0];
			}
			//密码太长
			else if(score == -3){
				strColor = '#f00';
				strText = options.hintText[1];
			}
			//密码不安全
			else if(score == -4){
				strColor = '#f00';
				strText = options.hintText[2];
			}
			//弱
			else if(score == 1)
			{
		   		strColor = options.colors[0];
				strText = options.verdects[0];
			}
			//中等
			else if (score == 2)
			{
		   		strColor = options.colors[1];
				strText = options.verdects[1];
			}
			//强
			else if (score == 3)
			{
			   	strColor = options.colors[2];
				strText = options.verdects[2];
				//$(ctlBar).css({width: "50%"});
			}
			$(ctlBar).css({backgroundColor: strColor});
			$(ctlText).html("<span style='color: " + strColor + ";'>" + strText + "</span>");
		},
		//校验密码,得到分数
		_checkPassword: function (password, options){
			var intScore = 0;
			var strVerdict = options.verdects[0];	
			var space = options.maxLength - options.minLength;
			
			if(space < 0){
				return -1;
			}
			var step = space / 3;
			// PASSWORD LENGTH
			if (password.length < options.minLength)                         // Password too short
			{
				intScore = - 2;
			}
			else if(password.length > options.maxLength){                    //too long
				intScore = -3;
			}
			else if (password.length >= options.minLength && password.length <= (options.minLength + step)) // Password short
			{
				intScore = 1
			}
			else if (password.length >= (options.minLength + step) && password.length <= (options.minLength + step*2))// Password Medium
			{
				intScore = 2
			}
			else if (password.length >= (options.minLength + step*3))                    // Password Large
			{
				intScore = 3
			}
			//密码与不安全密码一样
			for (var i=0; i < options.common.length; i++) {
				if (password.toLowerCase() == options.common[i]) {
					intScore = -4;
				}
			}
			return intScore;
		},
        _create:function(){
			var obj = this.element;
			var opt = this.options;
			var self = this;
			self.wrapper = $('<span></span>');
				//.addClass('ui-widget');
			//obj.addClass('ui-state-default ui-widget-content ui-corner-left ui-corner-right')
			obj.wrap(self.wrapper);
			if(opt.maxLength - opt.minLength < 0){
				opt.hint = false;
			}
			if(opt.hint){
				var infoarea = $(obj).attr('id');
				$(obj).after('<div class="pstrength-minLength" id="' + infoarea + '_minLength">密码的长度为: ' + opt.minLength + '---' + opt.maxLength + '</div>');
				$(obj).after('<div class="pstrength-info" id="' + infoarea + '_text"></div>');
				$(obj).after('<div class="pstrength-bar" id="' + infoarea + '_bar" style="border: 1px solid white; font-size: 1px; height: 2px; width: 0px;"></div>');
				$(obj).keyup(function(){				
					self._runPassword($(obj).val(), infoarea, opt);
				});
			}	
        }
	});	
    $.widget("csiui.btext" , {
		options:{
			maxLength: -1,		
			hint:false,
			hintText: '还能输入:',
			css: 'counter',
			defaultValue:''
		},
		_calculate: function(target,opt){
			var count = $(target).val().length;
			var available = opt.maxLength - count;
			if(available < 0){
				var text = $(target).val().substring(0,opt.maxLength);
				$(target).val(text);
				return;
			} 
			if(opt.hint){
				$(target).next().html(opt.hintText + available);
			}
		},
        _create:function(){
			var obj = this.element;
			var opt = this.options;
			var self = this;
			self.wrapper = $('<span></span>');
				//.addClass('ui-widget');
			//obj.addClass('ui-state-default ui-widget-content ui-corner-left ui-corner-right')
			obj.wrap(self.wrapper);
			if(opt.maxLength != -1){
				if(opt.hint){
					$(obj).after('<span class="' + opt.css + '">'+ opt.hintText +'</span>');
				}
				self._calculate(obj,opt);
				$(obj).bind("keyup", function(){
						self._calculate(obj,opt)
				});
				$(obj).bind("change", function(){
						self._calculate(obj,opt)
				});
			}
			if(opt.defaultValue !=''){
				$(obj).val(opt.defaultValue).focus(function(){
					if($(obj).val() == opt.defaultValue){
						$(obj).val('');
					}
				}).blur(function(){
					if( $(obj).val() == '' ){
						$(obj).val(opt.defaultValue);
					}
				});
			}
        }
	});	
    $.widget("csiui.number" , {
		options:{
			fix: false,
			minNum: 0,
			maxNum: 10000,
			precision: 0
		},
		_fixValue: function(target){
			var opts = this.options;
			var val = parseFloat($(target).val()).toFixed(opts.precision);
			if (isNaN(val)){
				$(target).val('');
				return;
			}
			if (opts.minNum != null && opts.minNum != undefined && val < opts.minNum){
				$(target).val(opts.minNum.toFixed(opts.precision));
			} else if (opts.maxNum != null && opts.maxNum != undefined && val > opts.maxNum){
				$(target).val(opts.maxNum.toFixed(opts.precision));
			} else {
				$(target).val(val);
			}
		},
		_bindEvents: function(target){
			var self = this;
			$(target).unbind('.number');
			$(target).bind('keypress.number', function(e){
				if (e.which == 45){	//-
					return true;
				} if (e.which == 46) {	//.
					return true;
				}
				else if ((e.which >= 48 && e.which <= 57 && e.ctrlKey == false && e.shiftKey == false) || e.which == 0 || e.which == 8) {
					return true;
				} else if (e.ctrlKey == true && (e.which == 99 || e.which == 118)) {
					return true;
				} else {
					return false;
				}
			}).bind('paste.number', function(){
				if (window.clipboardData) {
					var s = clipboardData.getData('text');
					if (! /\D/.test(s)) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}).bind('dragenter.number', function(){
				return false;
			}).bind('blur.number', function(){
				if(self.options.fix){
					self._fixValue(target);
				}
			});
		},
        _create:function(){
			var obj = this.element;	
			obj.css("ime-mode","Disabled");//数字输入框
			var opt = this.options;
			self.wrapper = $('<span></span>');
				//.addClass('ui-widget');
			//obj.addClass('ui-state-default ui-widget-content ui-corner-left ui-corner-right')
			obj.wrap(self.wrapper);
			this._bindEvents(obj);		
        }
	});	
	var wrapperClasses = 'csiui-file-wrapper ui-widget',
		inputClasses = 'csiui-file-input ui-state-default ui-widget-content ui-corner-left',
		buttonClasses = 'csiui-file-button ui-state-default ui-widget-header ui-corner-right',
		buttonTextClasses = 'csiui-file-button-text',
		fileClasses = 'csiui-file',
		hoverClasses = 'ui-state-hover',
		activeClasses = 'ui-state-active',
		stateClasses = hoverClasses + ' ' + activeClasses;

	$.widget("csiui.file", {
		options: {
			buttonText: "浏览",
			inputText: "",
			accept:"",
			unaccept:"",
			filename:"",
			inputWidth:150,
            buttonWidth:"auto",
			hint:{
				denied:"请选择正确的文件，后缀为：{0}",
				wrongname:"请选择正确的文件，文件名为:{0}"
			}
		},
		_checkDeny: function(self){
			var opt = self.options;
			var filename = self._getText();
			if((opt.accept != "") || (opt.unaccept != "") ){
				var unfound = true,found=false;
				var index = filename.lastIndexOf('.');
				if(index == -1) return false;
				var suffix = filename.substring(index+1);
				if(opt.unaccept != ""){
                    var unaccepts = opt.unaccept.split("|");
                    for(var i=0;i<unaccepts.length;i++){
                        if(suffix == unaccepts[i]){
                            unfound = false;
                            break;
                        }
				    }
                }
				if(opt.accept != ""){
					var accepts = opt.accept.split("|");
					for(var i=0;i<accepts.length;i++){
						if(suffix == accepts[i]){
						    found = true;
							break;
						}
					}
				}else{
                    found = true;
                }
				return unfound && found;
			}else{
				return true;
			}
		},
		_checkName: function(self){
			if(self.options.filename == self._getText() 
				|| self.options.filename == "") {
				return true;
			}else{
				return false;
			}
		},
		_create: function(){
			var self = this,
			options = self.options;
			self.fileFile = self.element,
			self.fileWrapper = $('<span></span>')
				.addClass(wrapperClasses)
				.hover(function(){
					self.fileButton.addClass(hoverClasses);
				},function(){
					self.fileButton.removeClass(stateClasses);
				}).bind('mousemove.file',function(e){
					var x = (e.pageX - $(this).offset().left) - (self.fileFile.width() / 1.2);
					var y = (e.pageY - $(this).offset().top) - (self.fileFile.height() / 2);
					self.fileFile.css('top', y).css('left', x);
				}).bind('mousedown.file',function(e){
					self.fileButton.addClass(activeClasses);
				}).bind('mouseup.file',function(e){
					self.fileButton.removeClass(activeClasses);
				}),
			self.fileFile
				.addClass(fileClasses)
				.wrap(self.fileWrapper)
				.attr("hidefocus",true),
			self.fileInput = $('<span></span>')
				.addClass(inputClasses)
				.text(self._getText())
				.insertBefore(self.fileFile)
				.width(options.inputWidth),
			self.fileButtonText = $('<span></span>')
				.addClass(buttonTextClasses)
				.text(options.buttonText)
			self.fileButton = $('<span></span>')
				.addClass(buttonClasses)
				.insertAfter(self.fileInput)
				.html(self.fileButtonText)
				.width(options.buttonWidth);
			self.fileFile.bind('change.file',function(){
				self.fileInput.text(self._getText());
				if(!self._checkDeny(self)){
					var msg = options.hint.denied ;
                    msg = msg.replace("{0}" , options.accept);
                    msg = msg.replace("{1}" , options.unaccept);
                    jAlert(msg, I18N.promp);
					self.reset();
				} 
				if(!self._checkName(self)){
					var msg = options.hint.wrongname;
                    msg = msg.replace("{0}" , options.filename);
                    jAlert(msg, I18N.promp);
					self.reset();
				}
			}).bind('focusin.file',function(){
				self.fileButton.addClass(hoverClasses);
			}).bind('focusout.file',function(){
				self.fileButton.removeClass(hoverClasses);
			});

		},
		_getText: function(){
			var self = this;
			fileValue = self.getValue();
			inputTextValue = self.options.inputText;

			if(fileValue == ''){
				return inputTextValue;
			}else{
				var res;
				if(biz.isWindows){		
					res =  fileValue.substring(fileValue.lastIndexOf("\\")+1);
				}else{
					res = fileValue.substring(fileValue.lastIndexOf("\/")+1);
				}
				return res;
				
			}
		},
		getValue: function(){
			var self = this;
			return fileValue = self.fileFile.val();
		},
		reset: function() {
			var self = this;
			self.fileInput.text(self.options.inputText);
		},
		destroy: function(){
			var self = this;
			self.fileInput.remove();
			self.fileButton.remove();
			self.fileButtonText.remove();
			self.fileFile.removeClass(fileClasses).unwrap(self.fileWrapper);
			self.fileWrapper.remove();
			$.Widget.prototype.destroy.call( self );
		},
		_setOption: function(option, value){
			var self = this;
			$.Widget.prototype._setOption.apply( self, arguments );
			switch(option){
				case "buttonText":
					self.fileButtonText.text(value);
					break;
				case "inputText":
					self.fileInput.text(self._getText());
					break;
			}
		}
	});
	$.widget("ui.checkbox", {
        options:{
            url:"",
            separator:",",
            value:undefined,
            data:"",
            ajaxOptions:{},
            name:""//name属性，对应生成input的name
        },
        _create: function() {
            var opt = this.options,$t=this.element;
            var id = $(this.element).attr("id");
            if(opt.data != ""){
                $(this.element).empty();
                /*修改数据格式为:[{name:"男",value:"1"},{name:"女",value:"0"}],
                 如为旧格式:[{name:"sex",label:"男",value:"1"},{name:"sex",label:"女",value:"0"}]则不对格式进行处理*/
                $(opt.data).each(function(i,dataObj){
                	if(opt.data[i].label==undefined){
                        opt.data[i].label = dataObj.name;
                        opt.data[i].name = opt.name;
                    }
                })
                $.tmpl("checkboxTmpl",opt.data).appendTo($t);
                if(opt.value){
                    //value支持["a","b"]或者"a,b"两种格式
                    valueArray = $.isArray(opt.value) ? opt.value : opt.value.split(opt.separator);
                    for(var val in valueArray){
                        var sel = $($t).find(" input[value='" + valueArray[val] + "']");
                        //$(sel).attr("checked", true);
                        $(sel)[0].checked = true;
                    }
                }
            }
            if(opt.url != ""){
	            var _ajaxOptions = opt.ajaxOptions; 
	            var _ajaxOptionsSuccess = _ajaxOptions.success;
	            var newSuccessF = {
                    dataType: "json",
                    type:"POST",
                    url: opt.url,
                    async:false,
                    success: function(data) {
                        $("#"+id).empty();
                        $(data).each(function(i,dataObj){
                            if(data[i].label==undefined){
                                data[i].label = dataObj.name;
                                data[i].name = opt.name;
                            }
                        })
                        $.tmpl("checkboxTmpl",data).appendTo($t);
                        if(opt.value){
                        	valueArray = $.isArray(opt.value) ? opt.value : opt.value.split(opt.separator);
                            for(var val in valueArray){
                                var sel = $($t).find(" input[value='" + valueArray[val] + "']");
                                //$(sel).attr("checked", true);
                                $(sel)[0].checked = true;
                            }
                        }
                        if(_ajaxOptionsSuccess){	
                        	_ajaxOptionsSuccess.call(this,data);
                        }
                    }        
                };
                _ajaxOptions.success = undefined;
                $.ajax($.extend(newSuccessF,_ajaxOptions));
            }
        },
        check: function(values) { 
            var disabled,$t = this.element;
            if($(values,$t).length > 0 && ($(values,$t)[0].nodeName || $(values,$t)[0].nodename)){//兼容旧版本api方式
                disabled = $(values,$t).attr("disabled");
                if(!disabled){
                    $(values,$t)[0].checked = true;
                }
            }else if(!!values){
                if(!$.isArray(values)){values = values.split(this.options.separator)}
                $.each(values,function(i,value){
                    var checkEle = $("input[value="+value+"]",$t);
                    if(!checkEle.attr("disabled")){checkEle[0].checked = true;}
                })
            }
        },
        uncheck: function(values) {
            var disabled,$t = this.element;
            if($(values,$t).length > 0 && ($(values,$t)[0].nodeName || $(values,$t)[0].nodename)){//兼容旧版本api方式
                disabled = $(values,$t).attr("disabled");
                if(!disabled){
                    $(values,$t)[0].checked = false;
                }
            }else if(!!values){
                if(!$.isArray(values)){values = values.split(this.options.separator)}
                $.each(values,function(i,value){
                    var checkEle = $("input[value="+value+"]",$t);
                    if(!checkEle.attr("disabled")){checkEle[0].checked = false;}
                })
            }
        },
        toggle: function(name) {
            var checkElement = name?"input[type=checkbox][name='"+name+"']":"input[type=checkbox]";
            $(checkElement,this.element).each(function(){
                var checked = $(this).attr("checked");
                var disabled = $(this).attr("disabled");
                if(!disabled){
                    $(this).attr("checked",!checked);
                }
            });
        },
        checkAll: function(name){
            var checkElement = name?"input[type=checkbox][name='"+name+"']":"input[type=checkbox]";
            $(checkElement,this.element).each(function(){
                var disabled = $(this).attr("disabled");
                if(!disabled){
                    $(this)[0].checked = true;
                }
            });
        },
        uncheckAll: function(name){
            var checkElement = name?"input[type=checkbox][name='"+name+"']":"input[type=checkbox]";
            $(checkElement,this.element).each(function(){
                var disabled = $(this).attr("disabled");
                if(!disabled){
                    $(this)[0].checked = false;
                }
            });
        },
        enable: function(values){
            var $t = this.element;
            if($(values,$t).length > 0 && ($(values,$t)[0].nodeName || $(values,$t)[0].nodename)){//兼容旧版本api方式
                $(values,$t).attr("disabled",false);
            }else if(!!values){
                if(!$.isArray(values)){values = values.split(this.options.separator)}
                $.each(values,function(i,value){
                    $("input[value="+value+"]",$t).attr("disabled",false);
                })
            }
        },
        disable: function(values){
            var $t = this.element;
            if($(values,$t).length > 0 && ($(values,$t)[0].nodeName || $(values,$t)[0].nodename)){//兼容旧版本api方式
                $(values,$t).attr("disabled",true);
            }else if(!!values){
                if(!$.isArray(values)){values = values.split(this.options.separator)}
                $(values).each(function(i,value){
                    $("input[value="+value+"]",$t).attr("disabled",true);
                })
            }
        },
        enableAll: function(name){
            var checkElement = name?"input[type=checkbox][name='"+name+"']":"input[type=checkbox]";
            $(checkElement,this.element).each(function(){
                $(this).attr("disabled",false);
            })
        },
        disableAll: function(name){
            var checkElement = name?"input[type=checkbox][name='"+name+"']":"input[type=checkbox]";
            $(checkElement,this.element).each(function(){
                $(this).attr("disabled",true);
            })
        },
        getCheckValues: function(name) {
            var str = "",separator = this.options.separator,
            	checkElement = name?"input[type=checkbox][name='"+name+"']:checked":"input[type=checkbox]:checked";
            $(checkElement,this.element).each(function(i){
                str+= (((i==0)?"":separator) + $(this).val());
            }) ;
            return str;
        },
        getUncheckValues: function(name) {
            var str = "",separator = this.options.separator,
            	checkElement = name?"input[type=checkbox][name='"+name+"']:not(:checked)":"input[type=checkbox]:not(:checked)";
            $(checkElement,this.element).each(function(i){
                str+= (((i==0)?"":separator) + $(this).val());
            }) ;
            return str;
        },
		setValue:function(values){
			this.uncheckAll();
			this.check(values);
		},
		getValue:function(){
			return this.getCheckValues();
		}
    });
    $.widget("ui.radio", {
        options:{
            url:"",
            value:undefined,
            data:"",
            ajaxOptions:{},
            separator:",",
            name:""//name属性，对应生成input的name
        },
        _create: function() {
            var opt = this.options,tempValue = opt.value,$t=this.element;
            var id = $(this.element).attr("id");
            if(opt.data != ""){
                $(this.element).empty();
                /*修改数据格式为:[{name:"男",value:"1"},{name:"女",value:"0"}],
                 如为旧格式:[{name:"sex",label:"男",value:"1"},{name:"sex",label:"女",value:"0"}]则不对格式进行处理*/
                $(opt.data).each(function(i,dataObj){
                	if(opt.data[i].label==undefined){
                        opt.data[i].label = dataObj.name;
                        opt.data[i].name = opt.name;
                    }
                })
                $.tmpl("radioTmpl",opt.data).appendTo($t);
                if(tempValue){
                    if(!$.isArray(tempValue)){tempValue = tempValue.split(opt.separator)}
                    $(tempValue).each(function(i,value){
                        var sel = $($t).find(" input[value='" + value + "']");
                        //$(sel).attr("checked", true);
                        $(sel)[0].checked = true;
                    })
                }
            }
            if(opt.url != ""){
            	var _ajaxOptions = opt.ajaxOptions; 
	            var _ajaxOptionsSuccess = _ajaxOptions.success;
	            var newSuccessF = {
	                    dataType: "json",
	                    type:"POST",
	                    url: opt.url,
	                    async:false,
	                    success: function(data) {
	                        $("#"+id).empty();
	                        $(data).each(function(i,dataObj){
	                        	if(data[i].label==undefined){
	                                data[i].label = dataObj.name;
	                                data[i].name = opt.name;
	                            }
	                        })
	                        $.tmpl("radioTmpl",data).appendTo($t);
	                        if(tempValue){
	                            if(!$.isArray(tempValue)){tempValue = tempValue.split(opt.separator)}
	                            $(tempValue).each(function(i,value){
	                                var sel = $($t).find(" input[value='" + value + "']");
	                                //$(sel).attr("checked", true);
	                                $(sel)[0].checked = true;
	                            })
	                        }
	                        if(_ajaxOptionsSuccess){	
	                        	_ajaxOptionsSuccess.call(this,data);
	                        }
	                    }
	                };
	            _ajaxOptions.success = undefined;
                $.ajax($.extend(newSuccessF,_ajaxOptions));
            }
        },
        check: function(value) {
            var $t = this.element;
            if($(value,$t).length > 0 && ($(value,$t)[0].nodeName || $(value,$t)[0].nodename)){//兼容旧版本api方式
                if(!$(value,$t).attr("disabled")){
                    $(value,$t)[0].checked = true;
                }
            }else if(!!value){
                if(!$.isArray(value)){value = value.split(this.options.separator)}
                $(value).each(function(i,tempvalue){
                    var checkEle = $("input[value="+tempvalue+"]",$t);
                    if(!checkEle.attr("disabled")){checkEle[0].checked = true;}
                })
            }
        },
        uncheck: function(value) {
            var $t = this.element;
            if($(value,$t).length > 0 && ($(value,$t)[0].nodeName || $(value,$t)[0].nodename)){//兼容旧版本api方式
                if(!$(value,$t).attr("disabled")){
                    $(value,$t)[0].checked = false;
                }
            }else  if(!!value){
                if(!$.isArray(value)){value = value.split(this.options.separator)}
                $(value).each(function(i,tempvalue){
                    var checkEle = $("input[value="+tempvalue+"]",$t);
                    if(!checkEle.attr("disabled")){checkEle[0].checked = false;}
                })
            }
        },
        enable: function(values){
            var $t = this.element;
            //如果values为空，所有radio启用
            if(!values){$("input",$t).each(function(){$(this).attr("disabled",false);});return;}
            if($(values,$t).length > 0 && ($(values,$t)[0].nodeName || $(values,$t)[0].nodename)){//兼容旧版本api方式
                $(values,$t).attr("disabled",false);
            }else  if(!!values){
                if(!$.isArray(values)){values = values.split(this.options.separator)}
                $.each(values,function(i,value){
                    $("input[value="+value+"]",$t).attr("disabled",false);
                })
            }
        },
        disable: function(values){
            var $t = this.element;
            //如果values为空，所有radio禁用
            if(!values){$("input",$t).each(function(){$(this).attr("disabled",true);});return;}
            if($(values,$t).length > 0 && ($(values,$t)[0].nodeName || $(values,$t)[0].nodename)){//兼容旧版本api方式
                $(values,$t).attr("disabled",true);
            }else if(!!values){
                if(!$.isArray(values)){values = values.split(this.options.separator)}
                $(values).each(function(i,value){
                    $("input[value="+value+"]",$t).attr("disabled",true);
                })
            }
        },
        getCheckValue: function(name) {
            var checkElement = name?"input[type=radio][name='"+name+"']:checked":"input[type=radio]:checked";
            return  $(checkElement,this.element).val();
        },
        getUncheckValue: function(name) {
            var str = "" ,separator = this.options.separator,
            	checkElement = name?"input[type=radio][name='"+name+"']:not(:checked)":"input[type=radio]:not(:checked)";
            $(checkElement,this.element).each(function(){
                str = (str == ""? "":(str+separator)) + $(this).val();
            })
            return  str;
        },
		setValue:function(value){
			this.check(value);
		},
		getValue:function(){
			return this.getCheckValue();
		}
    });
    var borderOffset = 6;//1+1+2+2
    var gap = 4;//invisible blank
    var elemWidth = 0;
    var leftOffset = 0;
    var rightOffset = 0;
    $.widget("csiui.form" , {
		options:{
            isResize:false,
            isBreak:false,
            inputWidth:'100',
            commonClass:'',
            labelSize:6,
            fontSize:'12',
            appendColon:false,
            asterisk:false
		},
		_calculateCols: function() {
            return parseInt(this.element.width() / (elemWidth + this.options.fontSize * 0.5));
        },
		_calculateWholeWidth: function(colNum) {
            return elemWidth * colNum + (colNum - 1) * gap - leftOffset - rightOffset;
        },
		_setInputWidth: function(cols, input) {
            if (cols <= this._calculateCols()) {
                input.css('width', this._calculateWholeWidth(cols) - borderOffset + 'px');
            }
            else
                input.css('width', this.options.inputWidth + 'px');
        },
        _resizeElements: function () {
			var self = this;
            var colNum = self._calculateCols();
            if (colNum == parseInt(self.element.data('rowNum')))
                return;
            self.element.data('colNum', colNum);
            //textarea
            var w = self._calculateWholeWidth(colNum);
            $('textarea').each(function() {
                if (!$(this).hasClass('editor')) 
					w -= borderOffset * 2;
                $(this).css({
                    'width':  w + 'px',
                    'height': parseInt(w * 0.4) + 'px'
                });
            });
            //input,div
            $('[cols]', self.element).each(function() {
                self._setInputWidth(parseInt($(this).attr('cols')), $(this).children('input,div'));
            });
        },
        _create:function(){
			var self = this;
            var formElement = this.element;
			var settings = this.options;
            //calculate auto fit width
            elemWidth = settings.fontSize * (settings.labelSize * 1 + (0.5 + 0.4)) + settings.inputWidth * 1 + 6;
            leftOffset = settings.fontSize * (settings.labelSize * 1 + 0.4);
            rightOffset = settings.fontSize * 0.5;
            var colNum = this._calculateCols();
            formElement.data('colNum', colNum);

            var elements = $('>label,>div:has(div),>fieldset>label,>fieldset>div', formElement);
            var labels = elements.children('span,strong');
            var inputs = elements.children(':text,:password,select,textarea,div');
            formElement.css({
                'font-size':settings.fontSize + 'px'
            }).find('select,fieldset,input,textarea').addClass(settings.commonClass);
            if (settings.labelSize)
                labels.css('width', settings.labelSize + 'em');
            if (settings.appendColon) {
                labels.each(function() {
                    $(this).html($(this).html() + ':');
                })
            }
            inputs.each(function() {
                if ($(this).hasClass('required') && settings.asterisk) {
                    $(this).after('<em class="required">*</em>');
                }
               // $(this).addClass('ui-widget ui-widget-content ui-corner-left ui-corner-right');
            });

            elements.css('display', settings.isBreak ? 'block' : 'inline-block').addClass('clearfix');

            if (settings.isResize) {
                $(window).resize(function() {
                    self._resizeElements();
                });
            }

            //set inputs width
            var w = this._calculateWholeWidth(colNum);
            inputs.each(function() {
                if (!$(this).attr('style')) {
                    if ($(this).is('select')) {
                        $(this).css('width', parseInt(settings.inputWidth) + settings.fontSize * 0.3 + 2 + 'px');
                    } else if ($(this).is('textarea')) {//textarea
                        if (!$(this).hasClass('editor')) w -= borderOffset * 2;
                        $(this).css({
                            'width':  w + 'px',
                            'height': parseInt(w * 0.4) + 'px'
                        });
                    } else {//text,password,div
                        self._setInputWidth(parseInt($(this).parent().attr('cols')), $(this));
                    }
                }//if not style
            });

            $('p>.save', this).click(function() {
                formElement.submit();
            });
            $('p>.reset', this).click(function() {
                formElement.reset();
            });
        }
	});	
    /* $.timePicker = function (elm) {
        var e = $(elm)[0];
         alert(e.timePicker);
         return e.timePicker;
     }; */
    $.widget("csiui.timepicker" , {
        options:{
			step:30,
            startTime: "00:00:00",
            endTime: "23:30:00",
            separator: ':',
            show24Hours: true
		    },
        timePicker:function(target,opt){
           var self = this;
           var e = $(target)[0];
          // return e.timePicker || (e.timePicker = new self._timePicker(e, opt));
           return e.timePicker || self._timePicker(e, opt);
         },
         _timePicker:function(target,opt){
             var self = this;
             var tpOver = false;
             var keyDown = false;
             var startTime = self.timeToDate(opt.startTime , opt);
             var endTime = self.timeToDate(opt.endTime , opt);
             var selectedClass = "selected";
             var selectedSelector = "li." + selectedClass;
            
             $(target).attr('autocomplete', 'OFF'); // Disable browser autocomplete
            
             var times = [];
             var time = new Date(startTime); // Create a new date object.
             while(time <= endTime) {
               times[times.length] = self.formatTime(time, opt);
               time = new Date(time.setMinutes(time.getMinutes() + opt.step));
             }
            
             var $tpDiv = $('<div class="time-picker'+ (opt.show24Hours ? '' : ' time-picker-12hours') +'"></div>');
             var $tpList = $('<ul></ul>');
            
             // Build the list.
             for(var i = 0; i < times.length; i++) {
               $tpList.append("<li>" + times[i] + "</li>");
             }
             $tpDiv.append($tpList);
             // Append the timPicker to the body and position it.
             $tpDiv.appendTo('body').hide();
            
             // Store the mouse state, used by the blur event. Use mouseover instead of
             // mousedown since Opera fires blur before mousedown.
             $tpDiv.mouseover(function() {
               tpOver = true;
             }).mouseout(function() {
               tpOver = false;
             });
            
             $("li", $tpList).mouseover(function() {
               if (!keyDown) {
                 $(selectedSelector, $tpDiv).removeClass(selectedClass);
                 $(self).addClass(selectedClass);
               }
             }).mousedown(function() {
                tpOver = true;
             }).click(function() {
               self.setTimeVal(target, this, $tpDiv, opt);
               tpOver = false;
             });
            
             var showPicker = function() {
               if ($tpDiv.is(":visible")) {
                 return false;
               }
               $("li", $tpDiv).removeClass(selectedClass);
            
               // Position
               var elmOffset = $(target).offset();
               $tpDiv.css({'top':elmOffset.top + target.offsetHeight, 'left':elmOffset.left});
            
               // Show picker. This has to be done before scrollTop is set since that
               // can't be done on hidden elements.
               $tpDiv.show();
            
               // Try to find a time in the list that matches the entered time.
               var time = target.value ? self.timeStringToDate(target.value, opt) : startTime;
               var startMin = startTime.getHours() * 60 + startTime.getMinutes();
               var min = (time.getHours() * 60 + time.getMinutes()) - startMin;
               var steps = Math.round(min / opt.step);
               var roundTime = self.normaliseTime(new Date(0, 0, 0, 0, (steps * opt.step + startMin), 0));
               roundTime = (startTime < roundTime && roundTime <= endTime) ? roundTime : startTime;
               var $matchedTime = $("li:contains(" + self.formatTime(roundTime, opt) + ")", $tpDiv);
            
               if ($matchedTime.length) {
                 $matchedTime.addClass(selectedClass);
                 // Scroll to matched time.
                 $tpDiv[0].scrollTop = $matchedTime[0].offsetTop;
               }
               return true;
             };
             // Attach to click as well as focus so timePicker can be shown again when
             // clicking on the input when it already has focus.
             $(target).focus(showPicker).click(showPicker);
             // Hide timepicker on blur
             $(target).blur(function() {
               if (!tpOver) {
                 $tpDiv.hide();
               }
             });
             // Keypress doesn't repeat on Safari for non-text keys.
             // Keydown doesn't repeat on Firefox and Opera on Mac.
             // Using kepress for Opera and Firefox and keydown for the rest seems to
             // work with up/down/enter/esc.
             var event = ($.browser.opera || $.browser.mozilla) ? 'keypress' : 'keydown';
             $(target)[event](function(e) {
               var $selected;
               keyDown = true;
               var top = $tpDiv[0].scrollTop;
               switch (e.keyCode) {
                 case 38: // Up arrow.
                   // Just show picker if it's hidden.
                   if (showPicker()) {
                     return false;
                   };
                   $selected = $(selectedSelector, $tpList);
                   var prev = $selected.prev().addClass(selectedClass)[0];
                   if (prev) {
                     $selected.removeClass(selectedClass);
                     // Scroll item into view.
                     if (prev.offsetTop < top) {
                       $tpDiv[0].scrollTop = top - prev.offsetHeight;
                     }
                   }
                   else {
                     // Loop to next item.
                     $selected.removeClass(selectedClass);
                     prev = $("li:last", $tpList).addClass(selectedClass)[0];
                     $tpDiv[0].scrollTop = prev.offsetTop - prev.offsetHeight;
                   }
                   return false;
                   break;
                 case 40: // Down arrow, similar in behaviour to up arrow.
                   if (showPicker()) {
                     return false;
                   };
                   $selected = $(selectedSelector, $tpList);
                   var next = $selected.next().addClass(selectedClass)[0];
                   if (next) {
                     $selected.removeClass(selectedClass);
                     if (next.offsetTop + next.offsetHeight > top + $tpDiv[0].offsetHeight) {
                       $tpDiv[0].scrollTop = top + next.offsetHeight;
                     }
                   }
                   else {
                     $selected.removeClass(selectedClass);
                     next = $("li:first", $tpList).addClass(selectedClass)[0];
                     $tpDiv[0].scrollTop = 0;
                   }
                   return false;
                   break;
                 case 13: // Enter
                   if ($tpDiv.is(":visible")) {
                     var sel = $(selectedSelector, $tpList)[0];
                     self.setTimeVal(target, sel, $tpDiv, opt);
                   }
                   return false;
                   break;
                 case 27: // Esc
                   $tpDiv.hide();
                   return false;
                   break;
               }
               return true;
             });
             $(target).keyup(function(e) {
               keyDown = false;
             });
             // Helper function to get an inputs current time as Date object.
             // Returns a Date object.
             self.getTime = function() {
               return self.timeStringToDate(target.value, opt);
             };
             // Helper function to set a time input.
             // Takes a Date object or string.
             self.setTime = function(time) {
               target.value = self.formatTime(self.timeToDate(time, opt), opt);
               // Trigger element's change events.
               $(target).change();
             };
         } ,
         setTimeVal:function(target, sel, $tpDiv, opt) {
            // Update input field
            target.value = $(sel).text();
            // Trigger element's change events.
            $(target).change();
            // Keep focus for all but IE (which doesn't like it)
            if (!$.browser.msie) {
              target.focus();
            }
            // Hide picker
            $tpDiv.hide();
          },
         formatTime:function (time, opt) {
         	 var self = this;
           var h = time.getHours();
           var hours = opt.show24Hours ? h : (((h + 11) % 12) + 1);
           var minutes = time.getMinutes();
           var ss = time.getTime() % 60000;
               ss = (ss - (ss % 1000)) / 1000;
           return self.formatNumber(hours) + opt.separator + self.formatNumber(minutes) + opt.separator + self.formatNumber(ss) + (opt.show24Hours ? '' : ((h < 12) ? ' am' : ' pm'));
         },
         formatNumber:function (value) {
           return (value < 10 ? '0' : '') + value;
         },
         timeToDate:function(input, opt) {
             var self = this;
           return (typeof input == 'object') ? self.normaliseTime(input) : self.timeStringToDate(input, opt);
         },
         timeStringToDate:function (input, opt) {
         	 var self = this;
           if (input) {
             var array = input.split(opt.separator);
             var hours = parseFloat(array[0]);
             var minutes = parseFloat(array[1]);
             var second = parseFloat(array[2]);
         
             // Convert AM/PM hour to 24-hour format.
             if (!opt.show24Hours) {
               if (hours === 12 && input.indexOf('am') !== -1) {
                 hours = 0;
               }
               else if (hours !== 12 && input.indexOf('pm') !== -1) {
                 hours += 12;
               }
             }
             var time = new Date(0, 0, 0, hours, minutes, second);
             return self.normaliseTime(time);
           }
           return null;
         },
         normaliseTime:function (time) {
           time.setFullYear(2001);
           time.setMonth(0);
           time.setDate(0);
           return time;
         },
         getTime:function(){
         	var self = this;
         	var opt = this.options;
         	var tempTime = $(self.element).val();
         	return self.timeToDate(tempTime,opt); 	
         },
         setTime:function(time){
         	var self = this;
         	var opt = this.options;
               self.element.value = self.formatTime(self.timeToDate(time, opt), opt);
               // Trigger element's change events.
               $(self.element).change();	
         },
         
         _create:function(){
   	       var obj = this.element;	
   	       var opt=this.options;
   	       this.timePicker(obj,opt);
         }       
	});
	$.widget("ui.combobox", {
		options:{
			clear:true,
			url:"",
			data:"",
			value:"",//默认值
			ajaxOptions:{}
		},
		_create: function() {
			var opt = this.options,$t= this.element,
				id = $(this.element).attr("id"),
				paraSource = opt.data ? opt.data : [];
			if(opt.data != ""){
				if(opt.clear){
					$(this.element).empty();
				}
				$.tmpl("optionTmpl",opt.data).appendTo($t);
				if(opt.value != "")$($t).val(opt.value);
			}

			if(opt.url != ""){
				$.ajax($.extend({
					dataType: "json",
					type: "POST",
					url: opt.url,
					success: function(data) {
						paraSource = data;
						if(opt.clear)$("#"+id).empty();
						$.tmpl("optionTmpl",data).appendTo($t);
						if(opt.value != "")$($t).val(opt.value);
					},
					//添加错误信息处理
                    error:function(xhr,st,err){
                           biz.utils.loadError(xhr,st,err);
                    }
				},opt.ajaxOptions));
			}
			//绑定onchange事件
			$.isFunction(opt.onchange) ? $($t).on("change",function(e){
				var selectedValue = $t.val();
				var returnRes = [];
				for(var i=0;i<paraSource.length;i++){
					　if ( paraSource[i].value == selectedValue ) {
						returnRes = paraSource[i];
						break;
					　}
				}
				opt.onchange.call(this,e,returnRes);
			}) : "";
		}
	});
	//////////////////////
  $.ui = $.ui || {}; $.ui.cascade = $.ui.cascade || {}; 
  
  $.fn.cascade = function(opt) { 
	var parent = opt.parent,
		paraSource = opt.list ? opt.list : [];
	if( opt.event ){
		//namespace our event 
		opt.event = opt.event.replace('.cascade','') + '.cascade';
	}
	
    opt = $.extend({}, {	  
		list: [], //static list to use as datasource 
		timeout: 10,//delay before firing getList operation
		getList: function(select) { $(this).trigger("updateList", [opt.list]); }, //function to fetch datasource
		template: function(str) { return "<option value='" + str.value + "'>" + str.name + "</option>"; },//applied to each item in datasource      
		match: function(selectedValue) { return this.first == selectedValue; ;}, //'this' is the js object, or the current list item from 'getList',
		event: "change.cascade",	//event to listen on parent which fires the cascade
		getParentValue: function(parent) { return $(parent).val(); } //delegate for retrieving the parent element's value
    }, opt);
	
    if($.ui.cascade.ext) {
		for(var ext in $.ui.cascade.ext) {
	        if(opt[ext]) {
	          opt = $.extend(opt, $.ui.cascade.ext[ext](opt));
	          delete opt[ext];
	        }
		} 
	}
	
    return this.each(function() {  
		var source = $(parent);				
		var self = $(this);		  
		
		//bind any events in extensions to each instance
		if($.ui.cascade.event) {
			for(var e in $.ui.cascade.event) {					
				self.bind(e + ".cascade",[source],$.ui.cascade.event[e]);		        
			} 
		}
		
		$(source).bind(opt.event,function() {							
			self.trigger("loading.cascade",[source[0]]);
			
			var selectTimeout = $.data(self, "selectTimeout");			
			if(selectTimeout) { window.clearInterval(selectTimeout); }			
			$.data(self, "selectTimeout", window.setTimeout(function() { 				
					self.trigger("cascade"); 
			}, opt.timeout));					
			
		});
		
        self.bind("cascade", function() {	
          self.one("updateList", function(e, list) {	
        	paraSource = list;
            list = $(list)
              .filter(function() { return opt.match.call(this, opt.getParentValue(parent)); })
              .map(function() { 
                var node = $(opt.template(this))[0];				                
                return node; 
              });
			  
			self.empty();//clear the source/select
			
            if(list.length){ 
				self.html(list);				
			}
			
			self.trigger("loaded.cascade",[source[0]]);//be sure to fire even if there is no data
			
			if( self.is(":input") ) {
				self.trigger("change.cascade");
			}	
			if(opt.value != "") self.val(opt.value);
          });
		
          opt.getList.call(self[0],source);	//call with child element as this
		  
        });
        source.trigger(opt.event);
        //绑定onchange事件
        $.isFunction(opt.onchange) ? self.on("change",function(e){
			var selectedValue = self.val();
			var returnRes = [];
			for(var i=0;i<paraSource.length;i++){
				　if ( paraSource[i].value == selectedValue ) {
					returnRes = paraSource[i];
					break;
				　}
			}
			opt.onchange.call(this,e,returnRes);
		}) : "";
    });
  };
  	$.ui = $.ui || {};
	$.ui.cascade = $.ui.cascade || {};
	$.ui.cascade.ext = $.ui.cascade.ext || {};
	$.ui.cascade.event = $.ui.cascade.event || {};

	$.ui.cascade.ext.ajax = function(opt) {				
		var ajax = opt.ajax;//ajax options hash...not just the url
		return { getList: function(parent) { 					
			var _ajax = {};
			var $this = $(this);//child element
			var defaultAjaxOptions = {
				type: "POST",
				dataType: "json",
				success: function(json) { $this.trigger("updateList", [json]); },
				error:function(xhr,st,err){ biz.utils.loadError(xhr,st,err);},//添加错误信息处理
				data: $.isFunction( opt.datafuc )?opt.datafuc.call($this,ajax.data):$.extend(_ajax.data,ajax.data,{ val: opt.getParentValue(parent) })				
			};						
			//overwrite opt.ajax with required props (json,successcallback,data)		
			//this lets us still pass in handling the other ajax callbacks and options
			$.extend(_ajax,defaultAjaxOptions,ajax);	
			
			$.ajax(_ajax);		 		  
		} };
	};

	$.ui.cascade.ext.templateText = function(opt) {
	var template = $.makeTemplate(opt.templateText, "<%", "%>");
	return { template: function(obj) { return template(obj); } };
	};	
	
	/*these events are bound on every instance...so the indicator appears  on each target */
	/* 
	*	CSS: .cascade-loading: { background: transparent url("${staticDir}/Content/images/indicator.gif") no-repeat center; }
	*/
	$.ui.cascade.event.loading = function(e,source) { 		
		$(this).empty();				
		var position = {
			'z-index':'6000',
			'position':'absolute',
			'width':'16px'
		};				
		$.extend(position,$(this).offset());						
		position.top = position.top + 3;
		position.left = position.left + 3;				
		$("<div class='csiui-cascade-loading'>&nbsp;</div>").appendTo("body").css(position);
		$(this)[0].disabled = true;				
	};
	$.ui.cascade.event.loaded = function(e,source) { 		
		$(this)[0].disabled = false;
		$(".csiui-cascade-loading").remove();		
	};
	
  $.makeTemplate = function (template, begin, end) {
    var rebegin = begin.replace(/([\]{}[\\])/g, '\\$1');
    var reend = end.replace(/([\]{}[\\])/g, '\\$1');

    var code = "try { with (_context) {" +
      "var _result = '';" +
        template
          .replace(/[\t\r\n]/g, ' ')
          .replace(/^(.*)$/, end + '$1' + begin)
          .replace(new RegExp(reend + "(.*?)" + rebegin, "g"), function (text) {
            return text
              .replace(new RegExp("^" + reend + "(.*)" + rebegin + "$"), "$1")
              .replace(/\\/g, "\\\\")
              .replace(/'/g, "\\'")
              .replace(/^(.*)$/, end + "_result += '$1';" + begin);
          })
          .replace(new RegExp(rebegin + "=(.*?)" + reend, "g"), "_result += ($1);")
          .replace(new RegExp(rebegin + "(.*?)" + reend, "g"), ' $1 ')
          .replace(new RegExp("^" + reend + "(.*)" + rebegin + "$"), '$1') +
      "return _result;" +
    "} } catch(e) { return '' } ";

    return new Function("_context", code);
  };
  
  //autocomplete数据格式修改，item同时支持label和name
  $.extend($.ui.autocomplete.prototype, {
      _renderMenu: function( ul, items ) {
          var self = this;
          $.each( items, function( index, item ) {
              self._renderItem( ul, item );
          });
      },
      _renderItem:function(ul,item){
          return $("<li></li>").data("item.autocomplete",item).append($("<a></a>").text(item.name?item.name:item.label)).appendTo(ul)
      },
      _normalize: function( items ) {
          // assume all items have the right format when the first item is complete
          if ( items.length && (items[0].name || items[0].label) && items[0].value ) {
              return items;
          }
          return $.map( items, function(item) {
              if ( typeof item === "string" ) {
                  return {
                      name: item,
                      value: item
                  };
              }else if(!!item.label){
                  return $.extend({
                      label: item.label || item.value,
                      value: item.value || item.label
                  }, item );
              }else{
            	  return $.extend({
                      name: item.name || item.value,
                      value: item.value || item.name
                  }, item );
              }
              
          });
      }
  });
  $.extend( $.ui.autocomplete, {
      filter: function(array, term) {
          var matcher = new RegExp( $.ui.autocomplete.escapeRegex(term), "i" );
          return $.grep( array, function(value) {
              return matcher.test( value.name || value.label || value.value || value );
          });
      }
  });
  //自动完成分组功能
  $.widget( "custom.catcomplete", $.ui.autocomplete, {
      _renderMenu: function( ul, items ) {
          var self = this,
              currentCategory = "";
          $.each( items, function( index, item ) {
              if ( item.category != currentCategory ) {
                  ul.append( "<li class='ui-autocomplete-category'>" + item.category + "</li>" );
                  currentCategory = item.category;
              }
              self._renderItem( ul, item );
          });
      }
  });
})(jQuery);
/*
    http://www.JSON.org/json2.js
    2011-02-23

    Public Domain.

    NO WARRANTY EXPRESSED OR IMPLIED. USE AT YOUR OWN RISK.

    See http://www.JSON.org/js.html


    This code should be minified before deployment.
    See http://javascript.crockford.com/jsmin.html

    USE YOUR OWN COPY. IT IS EXTREMELY UNWISE TO LOAD CODE FROM SERVERS YOU DO
    NOT CONTROL.


    This file creates a global JSON object containing two methods: stringify
    and parse.

        JSON.stringify(value, replacer, space)
            value       any JavaScript value, usually an object or array.

            replacer    an optional parameter that determines how object
                        values are stringified for objects. It can be a
                        function or an array of strings.

            space       an optional parameter that specifies the indentation
                        of nested structures. If it is omitted, the text will
                        be packed without extra whitespace. If it is a number,
                        it will specify the number of spaces to indent at each
                        level. If it is a string (such as '\t' or '&nbsp;'),
                        it contains the characters used to indent at each level.

            This method produces a JSON text from a JavaScript value.

            When an object value is found, if the object contains a toJSON
            method, its toJSON method will be called and the result will be
            stringified. A toJSON method does not serialize: it returns the
            value represented by the name/value pair that should be serialized,
            or undefined if nothing should be serialized. The toJSON method
            will be passed the key associated with the value, and this will be
            bound to the value

            For example, this would serialize Dates as ISO strings.

                Date.prototype.toJSON = function (key) {
                    function f(n) {
                        // Format integers to have at least two digits.
                        return n < 10 ? '0' + n : n;
                    }

                    return this.getUTCFullYear()   + '-' +
                         f(this.getUTCMonth() + 1) + '-' +
                         f(this.getUTCDate())      + 'T' +
                         f(this.getUTCHours())     + ':' +
                         f(this.getUTCMinutes())   + ':' +
                         f(this.getUTCSeconds())   + 'Z';
                };

            You can provide an optional replacer method. It will be passed the
            key and value of each member, with this bound to the containing
            object. The value that is returned from your method will be
            serialized. If your method returns undefined, then the member will
            be excluded from the serialization.

            If the replacer parameter is an array of strings, then it will be
            used to select the members to be serialized. It filters the results
            such that only members with keys listed in the replacer array are
            stringified.

            Values that do not have JSON representations, such as undefined or
            functions, will not be serialized. Such values in objects will be
            dropped; in arrays they will be replaced with null. You can use
            a replacer function to replace those with JSON values.
            JSON.stringify(undefined) returns undefined.

            The optional space parameter produces a stringification of the
            value that is filled with line breaks and indentation to make it
            easier to read.

            If the space parameter is a non-empty string, then that string will
            be used for indentation. If the space parameter is a number, then
            the indentation will be that many spaces.

            Example:

            text = JSON.stringify(['e', {pluribus: 'unum'}]);
            // text is '["e",{"pluribus":"unum"}]'


            text = JSON.stringify(['e', {pluribus: 'unum'}], null, '\t');
            // text is '[\n\t"e",\n\t{\n\t\t"pluribus": "unum"\n\t}\n]'

            text = JSON.stringify([new Date()], function (key, value) {
                return this[key] instanceof Date ?
                    'Date(' + this[key] + ')' : value;
            });
            // text is '["Date(---current time---)"]'


        JSON.parse(text, reviver)
            This method parses a JSON text to produce an object or array.
            It can throw a SyntaxError exception.

            The optional reviver parameter is a function that can filter and
            transform the results. It receives each of the keys and values,
            and its return value is used instead of the original value.
            If it returns what it received, then the structure is not modified.
            If it returns undefined then the member is deleted.

            Example:

            // Parse the text. Values that look like ISO date strings will
            // be converted to Date objects.

            myData = JSON.parse(text, function (key, value) {
                var a;
                if (typeof value === 'string') {
                    a =
/^(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2}(?:\.\d*)?)Z$/.exec(value);
                    if (a) {
                        return new Date(Date.UTC(+a[1], +a[2] - 1, +a[3], +a[4],
                            +a[5], +a[6]));
                    }
                }
                return value;
            });

            myData = JSON.parse('["Date(09/09/2001)"]', function (key, value) {
                var d;
                if (typeof value === 'string' &&
                        value.slice(0, 5) === 'Date(' &&
                        value.slice(-1) === ')') {
                    d = new Date(value.slice(5, -1));
                    if (d) {
                        return d;
                    }
                }
                return value;
            });


    This is a reference implementation. You are free to copy, modify, or
    redistribute.
*/

/*jslint evil: true, strict: false, regexp: false */

/*members "", "\b", "\t", "\n", "\f", "\r", "\"", JSON, "\\", apply,
    call, charCodeAt, getUTCDate, getUTCFullYear, getUTCHours,
    getUTCMinutes, getUTCMonth, getUTCSeconds, hasOwnProperty, join,
    lastIndex, length, parse, prototype, push, replace, slice, stringify,
    test, toJSON, toString, valueOf
*/


// Create a JSON object only if one does not already exist. We create the
// methods in a closure to avoid creating global variables.

var JSON;
if (!JSON) {
    JSON = {};
}

(function () {
    "use strict";

    function f(n) {
        // Format integers to have at least two digits.
        return n < 10 ? '0' + n : n;
    }

    if (typeof Date.prototype.toJSON !== 'function') {

        Date.prototype.toJSON = function (key) {

            return isFinite(this.valueOf()) ?
                this.getUTCFullYear()     + '-' +
                f(this.getUTCMonth() + 1) + '-' +
                f(this.getUTCDate())      + 'T' +
                f(this.getUTCHours())     + ':' +
                f(this.getUTCMinutes())   + ':' +
                f(this.getUTCSeconds())   + 'Z' : null;
        };

        String.prototype.toJSON      =
            Number.prototype.toJSON  =
            Boolean.prototype.toJSON = function (key) {
                return this.valueOf();
            };
    }

    var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        gap,
        indent,
        meta = {    // table of character substitutions
            '\b': '\\b',
            '\t': '\\t',
            '\n': '\\n',
            '\f': '\\f',
            '\r': '\\r',
            '"' : '\\"',
            '\\': '\\\\'
        },
        rep;


    function quote(string) {

// If the string contains no control characters, no quote characters, and no
// backslash characters, then we can safely slap some quotes around it.
// Otherwise we must also replace the offending characters with safe escape
// sequences.

        escapable.lastIndex = 0;
        return escapable.test(string) ? '"' + string.replace(escapable, function (a) {
            var c = meta[a];
            return typeof c === 'string' ? c :
                '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
        }) + '"' : '"' + string + '"';
    }


    function str(key, holder) {

// Produce a string from holder[key].

        var i,          // The loop counter.
            k,          // The member key.
            v,          // The member value.
            length,
            mind = gap,
            partial,
            value = holder[key];

// If the value has a toJSON method, call it to obtain a replacement value.

        if (value && typeof value === 'object' &&
                typeof value.toJSON === 'function') {
            value = value.toJSON(key);
        }

// If we were called with a replacer function, then call the replacer to
// obtain a replacement value.

        if (typeof rep === 'function') {
            value = rep.call(holder, key, value);
        }

// What happens next depends on the value's type.

        switch (typeof value) {
        case 'string':
            return quote(value);

        case 'number':

// JSON numbers must be finite. Encode non-finite numbers as null.

            return isFinite(value) ? String(value) : 'null';

        case 'boolean':
        case 'null':

// If the value is a boolean or null, convert it to a string. Note:
// typeof null does not produce 'null'. The case is included here in
// the remote chance that this gets fixed someday.

            return String(value);

// If the type is 'object', we might be dealing with an object or an array or
// null.

        case 'object':

// Due to a specification blunder in ECMAScript, typeof null is 'object',
// so watch out for that case.

            if (!value) {
                return 'null';
            }

// Make an array to hold the partial results of stringifying this object value.

            gap += indent;
            partial = [];

// Is the value an array?

            if (Object.prototype.toString.apply(value) === '[object Array]') {

// The value is an array. Stringify every element. Use null as a placeholder
// for non-JSON values.

                length = value.length;
                for (i = 0; i < length; i += 1) {
                    partial[i] = str(i, value) || 'null';
                }

// Join all of the elements together, separated with commas, and wrap them in
// brackets.

                v = partial.length === 0 ? '[]' : gap ?
                    '[\n' + gap + partial.join(',\n' + gap) + '\n' + mind + ']' :
                    '[' + partial.join(',') + ']';
                gap = mind;
                return v;
            }

// If the replacer is an array, use it to select the members to be stringified.

            if (rep && typeof rep === 'object') {
                length = rep.length;
                for (i = 0; i < length; i += 1) {
                    if (typeof rep[i] === 'string') {
                        k = rep[i];
                        v = str(k, value);
                        if (v) {
                            partial.push(quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            } else {

// Otherwise, iterate through all of the keys in the object.

                for (k in value) {
                    if (Object.prototype.hasOwnProperty.call(value, k)) {
                        v = str(k, value);
                        if (v) {
                            partial.push(quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            }

// Join all of the member texts together, separated with commas,
// and wrap them in braces.

            v = partial.length === 0 ? '{}' : gap ?
                '{\n' + gap + partial.join(',\n' + gap) + '\n' + mind + '}' :
                '{' + partial.join(',') + '}';
            gap = mind;
            return v;
        }
    }

// If the JSON object does not yet have a stringify method, give it one.

    if (typeof JSON.stringify !== 'function') {
        JSON.stringify = function (value, replacer, space) {

// The stringify method takes a value and an optional replacer, and an optional
// space parameter, and returns a JSON text. The replacer can be a function
// that can replace values, or an array of strings that will select the keys.
// A default replacer method can be provided. Use of the space parameter can
// produce text that is more easily readable.

            var i;
            gap = '';
            indent = '';

// If the space parameter is a number, make an indent string containing that
// many spaces.

            if (typeof space === 'number') {
                for (i = 0; i < space; i += 1) {
                    indent += ' ';
                }

// If the space parameter is a string, it will be used as the indent string.

            } else if (typeof space === 'string') {
                indent = space;
            }

// If there is a replacer, it must be a function or an array.
// Otherwise, throw an error.

            rep = replacer;
            if (replacer && typeof replacer !== 'function' &&
                    (typeof replacer !== 'object' ||
                    typeof replacer.length !== 'number')) {
                throw new Error('JSON.stringify');
            }

// Make a fake root object containing our value under the key of ''.
// Return the result of stringifying the value.

            return str('', {'': value});
        };
    }


// If the JSON object does not yet have a parse method, give it one.

    if (typeof JSON.parse !== 'function') {
        JSON.parse = function (text, reviver) {

// The parse method takes a text and an optional reviver function, and returns
// a JavaScript value if the text is a valid JSON text.

            var j;

            function walk(holder, key) {

// The walk method is used to recursively walk the resulting structure so
// that modifications can be made.

                var k, v, value = holder[key];
                if (value && typeof value === 'object') {
                    for (k in value) {
                        if (Object.prototype.hasOwnProperty.call(value, k)) {
                            v = walk(value, k);
                            if (v !== undefined) {
                                value[k] = v;
                            } else {
                                delete value[k];
                            }
                        }
                    }
                }
                return reviver.call(holder, key, value);
            }


// Parsing happens in four stages. In the first stage, we replace certain
// Unicode characters with escape sequences. JavaScript handles many characters
// incorrectly, either silently deleting them, or treating them as line endings.

            text = String(text);
            cx.lastIndex = 0;
            if (cx.test(text)) {
                text = text.replace(cx, function (a) {
                    return '\\u' +
                        ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
                });
            }

// In the second stage, we run the text against regular expressions that look
// for non-JSON patterns. We are especially concerned with '()' and 'new'
// because they can cause invocation, and '=' because it can cause mutation.
// But just to be safe, we want to reject all unexpected forms.

// We split the second stage into 4 regexp operations in order to work around
// crippling inefficiencies in IE's and Safari's regexp engines. First we
// replace the JSON backslash pairs with '@' (a non-JSON character). Second, we
// replace all simple value tokens with ']' characters. Third, we delete all
// open brackets that follow a colon or comma or that begin the text. Finally,
// we look to see that the remaining characters are only whitespace or ']' or
// ',' or ':' or '{' or '}'. If that is so, then the text is safe for eval.

            if (/^[\],:{}\s]*$/
                    .test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@')
                        .replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']')
                        .replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {

// In the third stage we use the eval function to compile the text into a
// JavaScript structure. The '{' operator is subject to a syntactic ambiguity
// in JavaScript: it can begin a block or an object literal. We wrap the text
// in parens to eliminate the ambiguity.

                j = eval('(' + text + ')');

// In the optional fourth stage, we recursively walk the new structure, passing
// each name/value pair to a reviver function for possible transformation.

                return typeof reviver === 'function' ?
                    walk({'': j}, '') : j;
            }

// If the text is not JSON parseable, then a SyntaxError is thrown.

            throw new SyntaxError('JSON.parse');
        };
    }
}());


/*!
 * JQuery dropdownlist
 * BizFoundation V5.0.5 
 */

(function($){
	$.fn.dropdownlist=function(opts){
		var settings = {
						id:'',
						width:184,
                        height:"auto",
                        listwidth:198,
                        zindex:20000,
						columns:1, 
						checkbox:false,
						valuedway:"click",
						maxchecked:0,//默认无限选
						selectedtext:'',
						//usinglimit:true,
						requiredvalue:[],
						disabled:false,
						selectclass:'ddl-select', 
						listboxclass:'ddl-listbox',
						selected:"",
						data:[],//[{value:1,name:'北京'},{value:2,name:'上海'}]
						url:"",
						onchange:null,
						callback:{
							beforeConfirm:null,
							beforeCancel:null
							}
						};
		
		var stopBubble=function (a) {
		    var a = a || window.event;
		    if (a.stopPropagation) {
		        a.stopPropagation()
		    } else {
		        window.event.cancelBubble = true
		    }
		};


		return this.each(function(){
            opts.id = opts.id?$(opts.id).attr("id"):this.id;
			$.extend(settings , opts || {});
			settings.selected = settings.selected.split(",");
			this.settings = settings;
			//if(settings.id=='') throw new Error('dropdownlist 的id不能为空。');
			if(settings.checkbox){
				for(var _i=0;_i<settings.requiredvalue.length;_i++){
					if($.inArray(settings.requiredvalue[_i],settings.selected)==-1) {
						throw new Error(settings.id+I18N.dropdownlist_mustcheck_error);
					}
				}
				//判断设置默认选择的初始值的个数是否超出了maxchecked
				if(settings.maxchecked != 0 && settings.selected.length>settings.maxchecked)
					throw new Error(settings.id+I18N.dropdownlist_limit_error);
			}

            //by jdh   把对$this的操作放到contentElem上
            var $this = $(this);
           // var objid = $("#"+settings.id);//eval(settings.id);
            var contentElem ,$listContent,spanElem,$spanElem;
            //创建contentElem元素 ,不重复创建
            if(!$("#"+opts.id+"_listContent").length > 0){
                 contentElem=document.createElement("div");
                 $listContent = $(contentElem);
                 $(contentElem).attr("id",opts.id+"_listContent");
                 $(contentElem).addClass("dropdownlist");
                 $listContent.attr("style","display:none; position: absolute;z-index:" + settings.zindex + ";");
                 $("body").append($listContent);
            }else{
                $listContent = $("#"+opts.id+"_listContent");
            }
            //增加span标签
            spanElem=document.createElement("span");
            $spanElem = $(spanElem);
            $spanElem.addClass("droplist csiui-file-wrapper ui-widget");
            $spanElem.attr("id",$this.attr("id")+"_span");
            $spanElem.width(settings.width);
            $spanElem.live("click",function(){_showMenu();});
            $this.hide();
            $(this).after($spanElem);

            function _showMenu() {
            	if(settings.disabled){
            		//禁用下拉树，不做任何处理
                }else{
	                var cityOffset = $spanElem.offset();
	                $("#"+opts.id+"_listContent").css({width:settings.listwidth,height:settings.height,left:cityOffset.left + "px", top:cityOffset.top + $spanElem.outerHeight() + "px"}).slideDown("fast");
	                $("body").bind("mousedown", _onBodyDown);
	                $listContent.bind("focus", _ontargetDown);
	                var checkData = $this.val().split(',');
	                var tempval = "",temptxt = "" ;
	                for(var i=0;i<checkData.length;i++){
	                   if( $("input[value='"+checkData[i]+"']",$listContent).length>0){
	                        $("input[value='"+checkData[i]+"']",$listContent).attr('checked','checked');
	                        tempval = tempval + checkData[i] + ((i==(checkData.length-1))?"":",");
	                        temptxt = temptxt + $("input[value='"+checkData[i]+"']",$listContent).attr('txt')  + ((i==(checkData.length-1))?"":",");
	                   }
	                }
	                $this.attr("selectedTmpText", temptxt);
	                $this.attr("selectedTmpValue", tempval);
	                
	                //单选情况下，使被选值有底色，方便用户识别
					if(!settings.checkbox)
					{
						$("#"+opts.id+"_listContent td").each(function(){
							$(this).click(function(){
								$(".clickToSelect").removeClass("clickToSelect");
							
								$(this).addClass("clickToSelect");	
							})
						});
					}
                }
		    }
            function _hideMenu() {
            	//去掉被选值底色，防止点击下拉列表之外的页面后还保留底色的情况
				$("#"+opts.id+"_listContent .clickToSelect").removeClass("clickToSelect");
				$("#"+opts.id+"_listContent").fadeOut("fast");
                $("body").unbind("mousedown", _onBodyDown);
               // $listContent.unbind("keydown", _ontargetDown);
                $listContent.unbind("focus", _ontargetDown);
		    }
            function _onBodyDown(event) {
                if (!(event.target.id == opts.id || event.target.id == opts.id+"_menuContent" || $(event.target).parents("#"+opts.id+"_listContent").length>0)) {
                   // _setval();
                    _hideMenu();
                }
            }
            function _ontargetDown(event) {
                if ((event.target.id == opts.id || event.target.id == opts.id+"_menuContent" || $(event.target).parents("#"+opts.id+"_listContent").length>0)) {
                    _hideMenu();
                }
            }
            function _setval(){
                if(typeof($this.attr('selectedTmpValue'))!='undefined'){
						//modify by futh 修复“请选择”无法去掉的bug
                        var temtext = $this.attr('selectedTmpText');
                        var temvalue= $this.attr('selectedTmpValue');
						if(temvalue==""){
							$this.attr({'selectedText':I18N.dropdownlist_defaultValue,'selectedValue':""});

						}else{
							 var temvalue=$this.attr('selectedTmpValue');
							if(temtext.indexOf(I18N.dropdownlist_defaultValue)>-1 ){
								 temtext = temtext.substring(4,temtext.length);
								 temvalue = temvalue.substring(1,temvalue.length);
							 }
							$this.attr({'selectedText':temtext,'selectedValue':temvalue});
						}

						//select.attr({'selectedTmpText':'','selectedTmpValue':''});

					}
                    $this.text = $this.attr('selectedText');
					$this.value = $this.attr('selectedValue');
                    changeval();
                    $spanElem.text($this.attr('selectedText'));
            }
            function changeval(){
				//$('#'+settings.id).val(objid.value);
            	$this.val($this.value);
            	//$this.attr("value", $this.value);
            	$this.attr("text",$this.value);
				if($this.onchange)$this.onchange($this.text,$this.value);
				//给下拉列表添加change事件侦听用于去掉校验红框
				$this.trigger("change");
			}
            
            function beforeConfirm()
            {
				var text = $this.attr('selectedTmpText');
				var value = $this.attr('selectedTmpValue');
				if($this.beforeConfirm)
				{
					var result = $this.beforeConfirm.call(this, text, value);
					if(result != undefined)
						return result;
				}
				return true;
            }
            
            function beforeCancel()
            {
				var text = $this.attr('selectedTmpText');
				var value = $this.attr('selectedTmpValue');
				if($this.beforeCancel)
				{
					var result = $this.beforeCancel.call(this, text, value);
					if(result != undefined)
						return result; 
				}
				return true;
			}
            
			$listContent.empty();
			window[settings.id]={};
			$this.onchange=settings.onchange;
			$this.beforeConfirm = settings.callback.beforeConfirm;
			$this.beforeCancel = settings.callback.beforeCancel;
			
            if(navigator.userAgent.toLowerCase().indexOf('msie 6.0')!=-1)
            {
            	$this.mouseover(function(){
            		if(!$this.hasClass('hover'))$this.addClass('hover');
            	}).mouseout(function(){
            		if($this.hasClass('hover'))$this.removeClass('hover');
            	});
            }
			settings.listboxwidth = settings.listboxwidth<=0?$this.outerWidth():settings.listboxwidth;
            /*  by jdh 把table直接放到$listContent上
			var listbox = $('<div>',{
							'class':settings.listboxclass+' ddlclass',
							'html':'<table width="100%" cellpadding="5" cellspacing="0" border="0" style="line-height:12px; font-size: 12px;"><tbody></tbody></table>',
							'css': {'position':'absolute','width':settings.listboxwidth,'overflow':'hidden','overflow-y':'auto','display':'none','z-index':1000}
						}).appendTo($listContent);
                       // }).appendTo("body");
            */
            var listbox =  $listContent;
            var tfoot;
            if(!$("table","#"+opts.id+"_listContent").length > 0){
                 if( $.browser.msie && parseInt($.browser.version) <= 6 ){
                       $('<iframe id="iframeD" style="border:none;filter:alpha(opacity=0);-moz-opacity:0;position:absolute; z-index:-1"></iframe><table class="ddl-listbox ddclass" width="100%" height="100%" cellpadding="5" cellspacing="0" border="0" style="line-height:12px; font-size: 12px;"><tbody></tbody></table>')
                           .appendTo($listContent);
				 }else{
					    $('<table class="ddl-listbox ddclass" width="100%" height="100%" cellpadding="5" cellspacing="0" border="0" style="line-height:12px; font-size: 12px;"><tbody></tbody></table>')
                           .appendTo($listContent);
				 }
                 
               //单选
				 if(!settings.checkbox)
				 {
					//button方式选择，构造button；否则不构造
					if(settings.valuedway == "button")
					{
						tfoot = $('<tfoot><td colspan="'+settings.columns+'"><span class="btn-area" style="float:right;"><input type="button" value="'+I18N.dropdownlist_bConfirm+'" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-primary"><input type="button" value="'+I18N.dropdownlist_bCancel+'" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-primary"></span></td></tfoot>');
					}
				 }
				 //多选
				 else
				 {
					if(settings.valuedway == "click")
					{
						//限选无button
						if(settings.maxchecked > 0)
						{
							tfoot = $('<tfoot><td colspan="'+settings.columns+'"><span class="info" style="float:left;">'+I18N.dropdownlist_mostChecked_1+settings.maxchecked+I18N.dropdownlist_mostChecked_2+'</span></td></tfoot>');
						}
					}
					else
					{
						//限选有button
						if(settings.maxchecked > 0)
						{
							tfoot = $('<tfoot><td colspan="'+settings.columns+'"><span class="info" style="float:left;">'+I18N.dropdownlist_mostChecked_1+settings.maxchecked+I18N.dropdownlist_mostChecked_2+'</span><span class="btn-area" style="float:right;"><input type="button" value="'+I18N.dropdownlist_bConfirm+'" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-primary"><input type="button" value="'+I18N.dropdownlist_bCancel+'" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-primary"></span></td></tfoot>');
						}
						//不限选有button
						else
						{
							tfoot = $('<tfoot><td colspan="'+settings.columns+'"><span class="btn-area" style="float:right;"><input type="button" value="'+I18N.dropdownlist_bConfirm+'" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-primary"><input type="button" value="'+I18N.dropdownlist_bCancel+'" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-primary"></span></td></tfoot>');
						}
					}
				 } 	
                
                $('table',listbox).append(tfoot);

			}else{
                  tfoot = $("tfoot","#"+opts.id+"_listContent");
            }
            var _btn = $('input',tfoot);
			$(_btn[0]).click(function(){
				//增加点击确定按钮的事件
				if(beforeConfirm())
				{
					_setval();
                    $this.hide(true);
                    _hideMenu();
				}
			});
			$(_btn[1]).click(function(){
				//增加点击取消按钮的事件
				 if(beforeCancel())
				 {     
					$this.hide();
				    _hideMenu();
				 }
			});
			var table = $('table>tbody',listbox).get(0);	
			$this.disable=function(){
				settings.disabled=true;
				if(!$this.hasClass('ddl-disabled'))
				{
					$this.addClass('ddl-disabled');
					$this.next().addClass('ddl-disabled-forSpan');
				}
					
			};
			$this.enable=function(){
				$this.removeClass('ddl-disabled');
				$this.next().removeClass('ddl-disabled-forSpan');
				settings.disabled=false;
			};
			$this.init = function(mdata,selectedValue){
				var row,cell,chk,lbl;
				var index = 0;	
				if(!$.isArray(selectedValue)){selectedValue = selectedValue.split(",");}
				var selecttextarr = [],selectvaluearr = [];
				if(selectedValue){
					$.each(selectedValue,function(i,n){
                        var tmptxt = "";
                        //数据格式修改
                        for(var j=0;j<mdata.length;j++){
                            if( mdata[j].value == n){
                                 tmptxt = mdata[j].name;
                                 break;
                            }
                        }
						tmptxt = tmptxt.replace(/,/g,',');
						selecttextarr[selecttextarr.length]=tmptxt;
					});				
				}
				if(settings.selectedtext!=''){
					if(!$.isArray(settings.selectedtext)){
						selecttextarr = settings.selectedtext.split(",");
					}else{
						selecttextarr = settings.selectedtext ;
					}
					$.each(selecttextarr,function(i,n){
                        var tmpvalue = "";
                        //数据格式修改
                        for(var j=0;j<mdata.length;j++){
                            if( mdata[j].name == n){
                            	tmpvalue = mdata[j].value;
                                 break;
                            }
                        }
                        tmpvalue = tmpvalue.replace(/,/g,',');
						selectvaluearr[selectvaluearr.length]=tmpvalue;
					});			
					selectedValue = selectvaluearr;
				}
				var selecttext = selecttextarr.join(',');
				selecttext = selecttext==''?I18N.dropdownlist_defaultValue:selecttext;
				selecthtml = settings.checkbox&&settings.selectedtext!=''?settings.selectedtext:selecttext;
				var selectvalue = selectedValue.join(',');
				//select.attr({'selectedText':selecttext,'selectedValue':settings.selected.join(';')}).html(selecthtml);
				$this.attr({'selectedText':selecttext,'selectedValue':selectvalue}).val(selectvalue);
                $spanElem.html(selecthtml);
				$(table).html('');
				//数据格式修改为[{value:1,name:'北京'},{value:2,name:'上海'}]
				$.each(mdata,function(index,objelemt){
                    var k = objelemt.value,v = objelemt.name;				
		            if (index % settings.columns == 0) { row = table.insertRow(-1); }
		            cell = row.insertCell(index%settings.columns);
		            var $cell = $(cell);
		            if(navigator.userAgent.toLowerCase().indexOf('msie 6.0')!=-1)
		            {
		            	$cell.mouseover(function(){
		            		if(!$cell.hasClass('hover'))$cell.addClass('hover');
		            	}).mouseout(function(){
		            		if($cell.hasClass('hover'))$cell.removeClass('hover');
		            	});
		            }
		            lbl = v.replace(/,/g,',');
		            if(settings.checkbox)
		            {
		            	chk=$('<input>',{type:'checkbox',value:k,'txt':lbl});
		            	if($.inArray(k,selectedValue)!=-1)chk.attr('checked','checked');
		            	if($.inArray(k,settings.requiredvalue)!=-1){chk.attr('disabled','disabled');}
		            	$cell.append(chk);
		            }
		            
		            $cell.append(lbl);
		            $cell.attr('value',k).css({'cursor':'pointer'}).click(function(evt){
						var tmp = $('input',$(this));
						if(tmp.attr('disabled')){stopBubble(evt);return;}
						if(settings.checkbox){
							if(tmp.attr('disabled')=='disabled')return;
							var obj = evt.srcElement || evt.target;
							
							var tmptext = $this.attr('selectedTmpText');
							var tmpvalue = $this.attr('selectedTmpValue');
							tmptext = typeof(tmptext)!='undefined'?(tmptext!=''?tmptext.split(','):[]):$this.text.split(',');
							tmpvalue = typeof(tmpvalue)!='undefined'?(tmpvalue!=''?tmpvalue.split(','):[]):$this.value.split(',');
							
							if(obj.tagName && obj.tagName.toLowerCase()!='input'){
								if(tmp.attr('checked')) tmp.removeAttr('checked');
								else tmp.attr('checked','checked');
							}
							if(!tmp.attr('checked'))
							{
								var _i = $.inArray(tmp.val(),tmpvalue);	
								if(_i>-1){
									tmpvalue.splice(_i,1);
									tmptext.splice(_i,1);
								}
							}
							else{
								//如果已经存在就不再追加（因为在IE9及以上版本，双击checkbox会导致重复设置，考虑到下拉列表中的value不会存在重复的值，所以这样改合理）
								var exits = false;
								for(var i in tmpvalue) {  
							       if(tmpvalue[i]==tmp.val()) {
							    	   exits = true;
							       }
								}  
                                if(!exits){
                                	tmpvalue[tmpvalue.length]=tmp.val();
    								tmptext[tmptext.length]=tmp.attr('txt');
                                }
							}
							
							//判断所选个数是否超出设定个数
							if(settings.maxchecked != 0 && tmpvalue.length>settings.maxchecked){
								var _i=0
								for(;_i<tmpvalue.length;_i++)
								{
									if($.inArray(tmpvalue[_i],settings.requiredvalue)==-1)break;
								}
								$('input[value="'+tmpvalue[_i]+'"]',listbox).removeAttr('checked');
								tmpvalue.splice(_i,1);
								tmptext.splice(_i,1);
							}
							$this.attr({'selectedTmpText':tmptext.join(','),'selectedTmpValue':tmpvalue.join(',')});
							stopBubble(evt);
							if(settings.valuedway =="click") _setval();
						}else{
							$this.attr({'selectedTmpText':$(this).text(),'selectedTmpValue':$(this).attr('value'),'selectedText':$(this).text(),'selectedValue':$(this).attr('value')});
							$this.value = $this.attr('selectedValue');
							$this.text = $this.attr('selectedText');
                            if(settings.valuedway =="click") _setval();
						}
						if(!settings.checkbox)changeval();
		            });		            
					index++;
				});
				if(cell && index%settings.columns!=0)$(cell).attr('colspan',settings.columns+1-index%settings.columns);

				//if(listbox.height()>settings.listboxmaxheight)listbox.height(settings.listboxmaxheight).css({'overflow':'hidden','overflow-y':'auto'});
			};
			$this.hide = function(clear){
				if(settings.checkbox && !clear){
					$this.removeAttr('selectedTmpText');
					$this.removeAttr('selectedTmpValue');
					var tmpsv = $this.attr('selectedValue').split(';');
					$('input',listbox).each(function(){
						$(this).removeAttr('checked');
					});
					$.each(tmpsv,function(i,n){
						$('input[value="'+n+'"]',listbox).attr('checked','checked');
					});
				}
				$('div.'+settings.listboxclass,$listContent).hide();
			};
			this.disable = $this.disable;
            this.enable = $this.enable;
			function initdata(opt){
                var returnobj ;
                $.ajax($.extend({
                     type: "POST",
                     dataType:"json",
                     url: settings.url,
					 async:false,
                     success:function(data, textStatus, jqXHR){
                         returnobj =  data;
                     }
                  },opt));
                return returnobj;
			}
			
            var listdata;
            if(settings.data.length > 0){
                 listdata = settings.data;
                 $this.bind('reload', function(e,opt){
                     listdata = settings.data ;
                     $this.init(listdata,settings.selected);
                 })
            }
            //如果属性中url非空，则ajax请求数据
            if(settings.url != "" ){
                 listdata = initdata(settings.ajaxOptions);
                 $this.bind('reload', function(e,opt){
                     listdata = initdata(settings.ajaxOptions);
                     $this.init(listdata,settings.selected);
                 })
            }

            $this.init(listdata,settings.selected);
			$this.text = $this.attr('selectedText');
			$this.value = $this.attr('selectedValue');
		});
	};
	
})(jQuery);

// jQuery Alert Dialogs Plugin
//
// Version 1.1
//
// Cory S.N. LaViska
// A Beautiful Site (http://abeautifulsite.net/)
// 14 May 2009
//
// Visit http://abeautifulsite.net/notebook/87 for more information
//
// Usage:
//		jAlert( message, [title, callback] )
//		jConfirm( message, [title, callback] )
//		jPrompt( message, [value, title, callback] )
// 
// History:
//
//		1.00 - Released (29 December 2008)
//
//		1.01 - Fixed bug where unbinding would destroy all resize events
//
// License:
// 
// This plugin is dual-licensed under the GNU General Public License and the MIT License and
// is copyright 2008 A Beautiful Site, LLC. 
//
(function($) {
	
	$.alerts = {
	    
	     time:null,
		// These properties can be read/written by accessing $.alerts.propertyName from your scripts at any time
		
		verticalOffset: -75,                // vertical offset of the dialog from center screen, in pixels
		horizontalOffset: 0,                // horizontal offset of the dialog from center screen, in pixels/
		repositionOnResize: true,           // re-centers the dialog on window resize
		overlayOpacity: .3,                // transparency level of overlay
		overlayColor: '#cccccc',               // base color of overlay
		draggable: true,                    // make the dialogs draggable (requires UI Draggables plugin)
		okButton: '&nbsp;OK&nbsp;',         // text for the OK button
		cancelButton: '&nbsp;Cancel&nbsp;', // text for the Cancel button
		dialogClass: null,                  // if specified, this class will be applied to all dialogs
		
		// Public methods
		
		alert: function(message, title, callback) {
			if( title == null ) title = 'Alert';
			$.alerts._show(title, message, null, 'alert', function(result) {
				if( callback ) callback(result);
			});
		},
		
		confirm: function(message, title, callback) {
			if( title == null ) title = 'Confirm';
			$.alerts._show(title, message, null, 'confirm', function(result) {
				if( callback ) callback(result);
			});
		},
			
		prompt: function(message, value, title, callback) {
			if( title == null ) title = 'Prompt';
			$.alerts._show(title, message, value, 'prompt', function(result) {
				if( callback ) callback(result);
			});
		},
		
		// Private methods
		
		_show: function(title, msg, value, type, callback) {
			
			$.alerts._hide();
			$.alerts._overlay('show');
			var iframeContainer ="";
			if( $.browser.msie && parseInt($.browser.version) <= 6 ){
				  iframeContainer = "<iframe id='iframeContainer' style='margin-left:-1px;border: none;filter:alpha(opacity=0);-moz-opacity:0;position:absolute; z-index:-1'></iframe>";
			}
			$("BODY").append(
			  '<div id="popup_container"> ' +iframeContainer+
			    '<h1 id="popup_title" style="text-align:left;padding-left:5px;font-size:12px;"></h1>' +
			    '<div id="popup_content">' +
			      '<div id="popup_message"></div>' +
				'</div>' +
			  '</div>');
			
			if( $.alerts.dialogClass ) $("#popup_container").addClass($.alerts.dialogClass);
			
			// IE6 Fix
			var pos = ($.browser.msie && parseInt($.browser.version) <= 6 ) ? 'absolute' : 'fixed'; 
			
			$("#popup_container").css({
				position: pos,
				zIndex: 99999,
				padding: 0,
				margin: 0
			});
			
			$("#popup_title").text(title);
			$("#popup_content").addClass(type);
			$("#popup_message").text(msg);
			$("#popup_message").html( $("#popup_message").text().replace(/\n/g, '<br />') );
			
			$("#popup_container").css({
				minWidth: $("#popup_container").outerWidth(),
				maxWidth: $("#popup_container").outerWidth()
			});
		    
			
			$.alerts._reposition();
			$.alerts._maintainPosition(true);
			
			switch( type ) {
				case 'alert':
					$("#popup_message").after('<div id="popup_panel" style="text-align: right;"><button  value="' + $.alerts.okButton + '" id="popup_ok" /></div>');
				
					$("#popup_ok").button({ label:I18N.confirm}).click( function() {
					    if($.alerts.time){
						   clearTimeout($.alerts.time);
						   $.alerts.time = null;
						}
						$.alerts._hide();
						callback(true);
					});
					$("#popup_ok").focus().keypress( function(e) {
						if( e.keyCode == 13 || e.keyCode == 27 ) $("#popup_ok").trigger('click');
					});
				break;
				case 'confirm':
					$("#popup_message").after('<div id="popup_panel" style="text-align: right;"><button value="' + $.alerts.okButton + '" id="popup_ok" /> <button value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>');
					$("#popup_ok").button({ label:I18N.confirm}).click( function() {
						$.alerts._hide();
						if( callback ) callback(true);
					});
					$("#popup_cancel").button({ label:I18N.cancel}).click( function() {
						$.alerts._hide();
						if( callback ) callback(false);
					});
					$("#popup_ok").focus();
					$("#popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
				break;
				case 'prompt':
					$("#popup_message").append('<br /><input type="text" size="30" id="popup_prompt" />').after('<div id="popup_panel"><button value="' + $.alerts.okButton + '" id="popup_ok" /> <button value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>');
					$("#popup_prompt").width( $("#popup_message").width() );
					$("#popup_ok").button({ label:I18N.confirm}).click( function() {
						var val = $("#popup_prompt").val();
						$.alerts._hide();
						if( callback ) callback( val );
					});
					$("#popup_cancel").button({ label:I18N.cancel}).click( function() {
						$.alerts._hide();
						if( callback ) callback( null );
					});
					$("#popup_prompt, #popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
					if( value ) $("#popup_prompt").val(value);
					$("#popup_prompt").focus().select();
				break;
			}
		   if( $.browser.msie && parseInt($.browser.version) <= 6 ){
				$("#iframeContainer").css({
					width: $("#popup_container").outerWidth(),
					height: $("#popup_container").outerHeight()
				});
		   }
			
			// Make draggable
			if( $.alerts.draggable ) {
				try {
					$("#popup_container").draggable({ handle: $("#popup_title") });
					$("#popup_title").css({ cursor: 'move' });
				} catch(e) { /* requires jQuery UI draggables */ }
			}
		},
		
		_hide: function() {
			$("#popup_container").remove();
			$.alerts._overlay('hide');
			$.alerts._maintainPosition(false);
		},
		
		_overlay: function(status) {
			switch( status ) {
				case 'show':
					$.alerts._overlay('hide');
				    var iframeoverlay ="";
					if( $.browser.msie && parseInt($.browser.version) <= 6 ){
						  iframeoverlay = "<iframe id='iframeoverlay' style='border: none;height:100%;width:100%;filter:alpha(opacity=0);-moz-opacity:0;position:absolute;'></iframe>";
					}
					$("BODY").append('<div id="popup_overlay">'+iframeoverlay+'</div>');
					$("#popup_overlay").css({
						position: 'absolute',
						zoom: 1,
						zIndex: 99998,
						top: '0px',
						left: '0px',
						width: '100%',
						height: $(document).height(),
						background: $.alerts.overlayColor,
						opacity: $.alerts.overlayOpacity
					});
						
				break;
				case 'hide':
					$("#popup_overlay").remove();
				break;
			}
		},
		
		_reposition: function() {
			
			var top = (($(window).height() / 2) - ($("#popup_container").outerHeight() / 2)) + $.alerts.verticalOffset;
			var left = (($(window).width() / 2) - ($("#popup_container").outerWidth() / 2)) + $.alerts.horizontalOffset;
			if( top < 0 ) top = 0;
			if( left < 0 ) left = 0;

            // IE6 fix
			if( $.browser.msie && parseInt($.browser.version) <= 6 ) top = top + $(window).scrollTop();
			
			$("#popup_container").css({
				top: top + 'px',
				left: left + 'px'
			});
			$("#popup_overlay").height( $(document).height() );
			
		},
		
		_maintainPosition: function(status) {
			if( $.alerts.repositionOnResize ) {
				switch(status) {
					case true:
						$(window).bind('resize', $.alerts._reposition);
					break;
					case false:
						$(window).unbind('resize', $.alerts._reposition);
					break;
				}
			}
		}
		
	}
	
	// Shortuct functions
	jAlert = function(message, title, callback) {
		$.alerts.alert(message, title, callback);
	}
	
	jConfirm = function(message, title, callback) {
		$.alerts.confirm(message, title, callback);
	};
		
	jPrompt = function(message, value, title, callback) {
		$.alerts.prompt(message, value, title, callback);
	};
	jHide = function(timeout){
		if(timeout){
		
		   $.alerts.time = setTimeout($.alerts._hide,timeout);
		}
	};
})(jQuery);
/** 
组件名称： 
  左右双向选择组件。 

主要功能： 
  1> 提供两种风格-普通列表和树状列表。 
  2> 目前提供三个扩展函数 
    getSelectedValues：  获得选中的值，返回的是个包含节点编号的数组 
    getRows：            查看所有右侧记录的详细信息，返回的是个包含节点对象的二维数组 
    getSelectedRows：    查看选中记录的详细信息，返回的是个包含节点对象的二维数组 

备注说明： 
  树状风格时，选择上级会自动级联处理下级；选择下级则上级默认半选中；如果所有下级都选中，则上级选中。 
  一个页面只能使用一个组件（需要修改） 

*/ 

/*==================== 组件对象代码 ====================*/ 
/* 
1、构造方法 
2、数据检查过滤方法 
3、组件生成方法 
4、选中/取消方法 
5、全部选中/取消方法 
6、页面数据显示方法*/ 

//构造函数 
function GBPSelectList(opts) {
    this.width            =  opts.width ? opts.width : "auto";                    //是否允许多选
    this.height            = opts.height ? opts.height : "auto";        //选择框的长度
    this.multiple            = true;                    //是否允许多选 
    this.listSize            = opts.size ? opts.size : 20;        //选择框的长度
    this.isTreeStyle         = Boolean(opts.isTreeStyle);    //是否用树状形式显示数据
    this.isShowCode          = Boolean(opts.isShowCode);     //如果是树状形式，是否显示层次码
    this.ctrlStyle           = opts.ctrlStyle?opts.ctrlStyle:"ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only";               //按钮样式

    this.needOrder           = true;                   //是否需要排序。（只在普通形式下起作用，树状时必须排序）
 
    this.data                = opts.data;                   
    this.itemList            = this.data;                   //存放所有选择元素的数组
    this.url                 = opts.url ? opts.url:"" ;     //数据源
    this.ajaxOptions         = opts.ajaxOptions ? opts.ajaxOptions:{} ;    //ajax请求参数

    this.leftSelect          = null;                    //左边的选择框控件 
    this.rightSelect         = null;                    //右边的选择框控件 
    this.leftDiv             = null;                    //左边放置列表框的层 
    this.rightDiv            = null;                    //右边放置列表框的层 
    this.leftDiv_innerHTML   = "";                      //左侧选择框层的内部元素， 
    this.rightDiv_innerHTML  = "";                      //右侧选择框层的内部元素 
    this.leftLabel           = null;                    //左边的文字标签控件 
    this.rightLabel          = null;                    //右边的文字标签控件 

    this.namespace           = opts.id;               //控件名称的前缀
    this.leftDivName         = "divAll";                //左边放置列表框的层的名称 
    this.rightDivName        = "divChecked";            //右边放置列表框的层的名称 
    this.leftSelectName      = "sltAll";                //左边列表框的名称 
    this.rightSelectName     = "sltChecked";            //右边列表框的名称 
    this.selectButton        = "btnSelect";             //选中按钮的名称 
    this.selectAllButton     = "btnSelectAll";          //选中所有按钮的名称 
    this.cancelButton        = "btnCancel";             //取消选择按钮的名称 
    this.cancelAllButton     = "btnCancelAll";          //取消所有按钮的名称 

    this.o_children          = [];                      //一级节点列表 
    this.root                = this;                    //根级节点-组件对象 
    this.nodes               = {};                      //存放所有节点 
    var o_this               = this;                    //组件对象引用 
    
    this.onSelect  =  opts.onSelect ? opts.onSelect : null;
    this.onSelectAll  =  opts.onSelectAll ? opts.onSelectAll : null;
    this.onCancel  =  opts.onCancel ? opts.onCancel : null;
    this.onCancelAll  =  opts.onCancelAll ? opts.onCancelAll : null;

    this.buildNode = function(){                        //处理组件显示数据，建立节点列表对象 
        if(this.isTreeStyle){ 
            //构造树形显示风格 
            _build(o_this, 0); 
        }else{ 
            //构造列表显示风格 
            for(var i = 0; i < this.itemList.length; i++){ 
                new GBP_SL_Item(o_this, this.itemList[i]); 
            } 
        } 
    }; 

    //在子类里迭代，是为了效率考虑。 
    function _build(father, level, start){ 
        if(!start) start = 0; 
        for(var i = start; i < o_this.itemList.length; i++){ 
            curLevel = o_this.itemList[i][4]; 
            //是否属于本级节点 
            if(curLevel == (level + 1)){ 
                var obj = new GBP_SL_Item(father, o_this.itemList[i]); 
                //继续下层递归 
                _build(obj, curLevel, i + 1); 
            }else if(curLevel <= level){ 
                //结束本层递归 
                break; 
            } 
        } 
    } 
} 

//对数据进行检查、过滤、排序处理 
GBPSelectList.prototype.prepareData = function() { 
    var list = new Array(); 
    var arrays = this.itemList; 
    var _pre;
    // 如设置url则ajax请求数据
    if(this.url !=""){
        $.ajax($.extend({
                     type:"POST",
                     dataType:"json",
                     url: this.url,
                     success:function(data, textStatus, jqXHR){
                         arrays =  data;
                     },
                     async:false
                  },this.ajaxOptions));
        this.itemList = arrays;
    }

    //按显示名排序 
    function _sortByName(a,b){ 
        if(a[1] == b[1]) return 0 
        return (a[1] > b[1]) ? 1 : -1; 
    } 

     //按层次码排序 
    function _sortByTreeCode(a,b){ 
        if(a[3] == b[3]) return 0 
        return (a[3] > b[3]) ? 1 : -1; 
    } 

    //检查数据是否完整，过滤不完整数据 
    for(var n = 0; n < arrays.length; n++){ 
        var len = list.length; 
        //列表节点至少要求有两项数据 
        //树形节点至少要求有五项数据 
        if((!this.isTreeStyle && arrays[n].length >= 2) || (this.isTreeStyle && arrays[n].length >= 5)){
             list[len] = arrays[n]; 
        } 
    } 

    //排序 
    if(this.isTreeStyle){ 
        list.sort(_sortByTreeCode); 
    }else if(this.needOrder){ 
        list.sort(_sortByName); 
    } 

    //使用已经排序的节点列表替换原来未排序的节点列表 
    this.itemList = list; 
} 

//构造组件，并显示在页面上 
GBPSelectList.prototype.make = function() { 
    //检查、过滤、排序数据 
    this.prepareData(); 

    var txt , obj; 

    txt = "<table width='"+this.width +"' height='"+ this.height +"' style= 'border-collapse: collapse'  bordercolor= '#E6EFFA'  align='center' cellpadding='0' cellspacing='2'>"

              + " <tr align='center'>" 
          //   + "   <td valign='top' width='30%'><div align='left' style='FONT-SIZE: 9pt;padding-top:2px;padding-bottom:2px;'>可用列表</div>"
			   + "   <td valign='top' width='30%'>"
              + "     <div  id='" + this.namespace + this.leftDivName + "'></div>"
              + "   </td>" 
             + "   <td valign='middle' width='5%' >" 
             + "     <input type='button' name='" + this.namespace + this.selectButton + "' id='" + this.namespace + this.selectButton + "' class='" + this.ctrlStyle + "' style='margin-left:3px;margin-right:3px;' value='  >  '><br><br>"
              + "     <input type='button' name='" + this.namespace + this.selectAllButton + "'  id='" + this.namespace + this.selectAllButton + "' class='" + this.ctrlStyle + "' style='margin-left:3px;margin-right:3px;' value='  >> '><br><br>"
              + "     <input type='button' name='" + this.namespace + this.cancelButton + "' id='" + this.namespace + this.cancelButton + "' class='" + this.ctrlStyle + "' style='margin-left:3px;margin-right:3px;' value='  <  '><br><br>"
              + "     <input type='button' name='" + this.namespace + this.cancelAllButton + "' id='" + this.namespace + this.cancelAllButton + "' class='" + this.ctrlStyle + "' style='margin-left:3px;margin-right:3px;' value=' <<  '>"
              + "   </td>" 
           //  + "   <td valign='top' width='30%'><div align='left' style='FONT-SIZE: 9pt;padding-top:2px;padding-bottom:2px;'>已选择列表</div>"
			    + "   <td valign='top' width='30%'>"
              + "     <div  id='" + this.namespace + this.rightDivName + "' align='left'></div>"
              + "   </td>" 
             + " </tr>" 
             + "</table>" ; 

    ///生成对象 
	//document.write(txt) ;
    $("#"+this.namespace).append(txt);

    var oThis = this; 
    
    //关联控制对象 
    this.leftDiv = document.getElementById(this.namespace + this.leftDivName); 
    this.rightDiv = document.getElementById(this.namespace + this.rightDivName);
 
    //关联按钮单击事件 
    obj = document.getElementById(this.namespace + this.selectButton); 
    obj.onclick = function() {
    	 if(oThis.onSelect){//用户自定义选中事件，合并选中处理逻辑
 		   //判断是否多选模式 
 		    var values = new Array(); 
 		    //处理哪个对象 
 		    var obj = oThis.leftSelect; 
 		    if(oThis.multiple){ 
 		        for(var i = 0; i < obj.options.length; i++){ 
 		            if(obj.options[i].selected){ 
 		                values[values.length] = obj.options[i].value; 
 		            } 
 		        } 
 		    }else{ 
 		        if(obj.selectedIndex > -1){ 
 		            values[0] = obj[obj.selectedIndex].value; 
 		        } 
 		    } 
  		   oThis.onSelect.call(oThis,values);
  	   }
    	oThis.doSelect(1);
    };

    obj = document.getElementById(this.namespace + this.selectAllButton); 
    obj.onclick = function() { 
    	if(oThis.onSelectAll){
  		    var values = new Array();  
  		    var obj = oThis.leftSelect; 
  		    if(oThis.multiple){ 
  		        for(var i = 0; i < obj.options.length; i++){ 
  		                values[values.length] = obj.options[i].value; 
  		        } 
  		    }
   		   oThis.onSelectAll.call(oThis,values);
   	    }
    	oThis.doAll(1);
    }; 

    obj = document.getElementById(this.namespace + this.cancelButton); 
    obj.onclick = function() { 
    	if(oThis.onCancel){
  		    var values = new Array();  
  		    var obj = oThis.rightSelect; 
  		    if(oThis.multiple){ 
		        for(var i = 0; i < obj.options.length; i++){ 
		            if(obj.options[i].selected){ 
		                values[values.length] = obj.options[i].value; 
		            } 
		        } 
		    }else{ 
		        if(obj.selectedIndex > -1){ 
		            values[0] = obj[obj.selectedIndex].value; 
		        } 
		    } 
   		   oThis.onCancel.call(oThis,values);
   	    }
    	oThis.doSelect(-1);
    }; 

    obj = document.getElementById(this.namespace + this.cancelAllButton); 
    obj.onclick = function() { 
    	if(oThis.onCancelAll){
  		    var values = new Array();  
  		    var obj = oThis.rightSelect; 
  		    if(oThis.multiple){ 
		        for(var i = 0; i < obj.options.length; i++){ 
		                values[values.length] = obj.options[i].value; 
		        } 
		    }
   		   oThis.onCancelAll.call(oThis,values);
   	    }
    	oThis.doAll(-1) 
    }; 

    //生成节点对象列表 
    this.buildNode(); 

    //刷新页面显示 
    this.update(); 
}; 

//选中/取消选中 
GBPSelectList.prototype.doSelect = function(isSelect) { 
    var values = new Array(); 

    //处理哪个对象 
    var obj = (isSelect == 1) ? this.leftSelect : this.rightSelect; 

    //判断是否多选模式 
    if(this.multiple){ 
        for(var i = 0; i < obj.options.length; i++){ 
            if(obj.options[i].selected){ 
                values[values.length] = obj.options[i].value; 
            } 
        } 
    }else{ 
        if(obj.selectedIndex > -1){ 
            values[0] = obj[obj.selectedIndex].value; 
        } 
    } 

    if( values.length == 0) return; 

    //处理下级节点 
    for(var n = 0; n < values.length; n++){ 
        this.nodes[values[n]].doSelect(isSelect); 
    } 

    //刷新页面显示 
    this.update() 
}; 

//全部选中/取消 
GBPSelectList.prototype.doAll = function(isSelect) { 
    for(var i = 0; i < this.o_children.length; i++){ 
        this.o_children[i].doSelect(isSelect); 
    } 

    this.update() 
}; 

//更新select元素的界面显示 
GBPSelectList.prototype.update = function(){ 
    //用innerHTML来设置select元素，可以加快速度，减少屏幕闪烁 
    //给层元素赋初值 

    this.root.rightDiv_innerHTML = "<select id='" + this.namespace + this.rightSelectName + "' " +"name='" + this.namespace + this.rightSelectName + "' " + (this.multiple ? "multiple" : "") + " size='" + this.listSize + "' style='width:100%'>";
    this.root.leftDiv_innerHTML = "<select id='" + this.namespace + this.leftSelectName + "' " + (this.multiple ? "multiple" : "") + " size='" + this.listSize + "' style='width:100%'>";
 
    //处理子节点状态，根据子节点的状态设置父节点状态 
    for(var i = 0; i < this.o_children.length; i++){ 
        this.o_children[i].doStatus(); 
    } 
    //生成页面显示 
    for(var i = 0; i < this.o_children.length; i++){ 
        this.o_children[i].update(); 
    } 

    //直接用innerHTML，速度最快 
    this.leftDiv.innerHTML = this.root.leftDiv_innerHTML + "</select>";
    this.rightDiv.innerHTML = this.root.rightDiv_innerHTML + "</select>";

    //重新获取两侧select元素，并为其绑定双击事件 
    var oThis = this; 

    this.leftSelect = document.getElementById(this.namespace + this.leftSelectName);
    this.leftSelect.ondblclick = function() {    	 
    	 if(oThis.onSelect){//用户自定义选中事件，合并选中处理逻辑
    		   //判断是否多选模式 
    		    var values = new Array(); 
    		    //处理哪个对象 
    		    var obj = this; 
    		    if(this.multiple){ 
    		        for(var i = 0; i < obj.options.length; i++){ 
    		            if(obj.options[i].selected){ 
    		                values[values.length] = obj.options[i].value; 
    		            } 
    		        } 
    		    }else{ 
    		        if(obj.selectedIndex > -1){ 
    		            values[0] = obj[obj.selectedIndex].value; 
    		        } 
    		    } 
     		oThis.onSelect.call(oThis,values);
     	 }
    	 oThis.doSelect(1) ;
    }; 

    this.rightSelect  = document.getElementById(this.namespace + this.rightSelectName);
    this.rightSelect .ondblclick = function() { 
    	if(oThis.onCancel){//用户自定义选中事件，合并选中处理逻辑
 		   //判断是否多选模式 
 		    var values = new Array(); 
 		    //处理哪个对象 
 		    var obj = this; 
 		    if(this.multiple){ 
 		        for(var i = 0; i < obj.options.length; i++){ 
 		            if(obj.options[i].selected){ 
 		                values[values.length] = obj.options[i].value; 
 		            } 
 		        } 
 		    }else{ 
 		        if(obj.selectedIndex > -1){ 
 		            values[0] = obj[obj.selectedIndex].value; 
 		        } 
 		    } 
  		   oThis.onCancel.call(oThis,values);
  	    }
    	oThis.doSelect(-1) 
    }; 
}; 

/*==================== 节点对象代码 ====================*/ 
/* 
1、构造方法 
2、节点选择/取消方法 
3、节点状态处理方法 
4、节点页面显示实现方法*/ 

//节点对象 
function GBP_SL_Item(o_parent, o_item){ 
    this.father = o_parent;                                //上级节点引用 
    this.root = o_parent.root;                             //根级节点引用 
    this.o_children = [];                                  //下级节点数组 
    this.info = o_item;                                    //节点对象 
    this.status = o_item[2] ?  1 : -1 ;                    //节点是否默认选中标志 
    this.level = this.root.isTreeStyle ? o_item[4] : 1;    //节点级次 
    this.value = o_item[0];                                //节点编号 
    this.showText = o_item[1];                             //节点显示名称 

    //根据定义处理显示名称格式 
    if(this.root.isShowCode) 
        // 是否显示层级编码 
        this.showText = this.showText + " - " + o_item[3]; 

    if(this.level > 0) 
        //处理显示缩进 
        this.showText = "__________________".substr(0, (this.level - 1)).replace(/_/g, "&nbsp;&nbsp;") + this.showText;
 
    //加入父级节点的下级节点列表 
    o_parent.o_children[o_parent.o_children.length] = this; 

    //加入所有节点列表 
    this.root.nodes[this.value] = this; 
} 

//节点的选择函数 
GBP_SL_Item.prototype.doSelect = function(isSelected){ 
    this.status = isSelected; 

    //选择上级会自动选择其所有下级 
    for(var i = 0; i < this.o_children.length; i++){ 
        this.o_children[i].doSelect(isSelected); 
    } 
}; 

//递归处理指定节点及其下级节点状态 
GBP_SL_Item.prototype.doStatus = function(){ 
    //没有下级节点 
    if(this.o_children.length == 0) return this.status; 

    var selectedCount = 0, partSelectCount = 0, unselectedCount = 0; 
    var returnStatus = null; 

    for(var i = 0; i < this.o_children.length; i++){ 
        //递归处理下级节点状态 
        returnStatus = this.o_children[i].doStatus(); 

        if(returnStatus == 1) selectedCount += 1; 
        if(returnStatus == 0) partSelectCount += 1; 
        if(returnStatus == -1) unselectedCount += 1; 
    } 

    if(selectedCount > 0 && unselectedCount == 0 && partSelectCount == 0){ 
        //下级全部选中 
        this.status = 1; 
    }else if(unselectedCount > 0 && selectedCount == 0 && partSelectCount == 0){
         //下级一个都没选中 
        this.status = -1; 
    }else{ 
        //下级部分选中 
        this.status = 0; 
    } 

    return this.status; 
}; 

//更新界面显示 
GBP_SL_Item.prototype.update = function(){ 
    //status：1表示选中；0表示下级部分选中；-1表示未选 
    if(this.status == -1 || this.status == 0){ 
        this.root.leftDiv_innerHTML += "<option value='" + this.value + "'>" + this.showText + "</option>\n";
     } 

    if(this.status == 1 || this.status == 0){ 
        this.root.rightDiv_innerHTML += "<option value='" + this.value + "'>" + this.showText + "</option>\n";
     } 

    //递归处理下级节点 
    for(var i = 0; i < this.o_children.length; i++){ 
        this.o_children[i].update(); 
    } 
}; 

/*==================== 组件扩展方法 ====================*/ 

//获得选中的值，返回的是个包含节点编号的数组 
GBPSelectList.prototype.getSelectedValues = function() { 
    var values = new Array(); 
    for(var n = 0; n < this.root.rightSelect.options.length; n++){
        values[n] = this.root.rightSelect.options[n].value;
    } 
    return values; 
}; 

//查看右侧记录的详细信息，返回的是个包含节点对象的二维数组 
GBPSelectList.prototype.getRows = function() { 
    var values = new Array(); 
    for(var n = 0; n < this.root.rightSelect.options.length; n++){
        values[values.length] = this.root.nodes[this.root.rightSelect.options[n].value].info;
     } 
    return values; 
}; 

//查看选中记录的详细信息，返回的是个包含节点对象的二维数组 
GBPSelectList.prototype.getSelectedRows = function() { 
    var values = new Array(); 
    for(var n = 0; n < this.root.rightSelect.options.length; n++){
        if(this.root.rightSelect[n].selected)
            values[values.length] = this.root.nodes[this.root.rightSelect.options[n].value].info;
     } 
    return values; 
}; 
/*! Copyright (c) 2010 Brandon Aaron (http://brandonaaron.net)
 * Licensed under the MIT License (LICENSE.txt).
 *
 * Version 2.1.3-pre
 */

(function($){

$.fn.bgiframe = ($.browser.msie && /msie 6\.0/i.test(navigator.userAgent) ? function(s) {
    s = $.extend({
        top     : 'auto', // auto == .currentStyle.borderTopWidth
        left    : 'auto', // auto == .currentStyle.borderLeftWidth
        width   : 'auto', // auto == offsetWidth
        height  : 'auto', // auto == offsetHeight
        opacity : true,
        src     : 'javascript:false;'
    }, s);
    var html = '<iframe class="bgiframe"frameborder="0"tabindex="-1"src="'+s.src+'"'+
                   'style="display:block;position:absolute;z-index:-1;'+
                       (s.opacity !== false?'filter:Alpha(Opacity=\'0\');':'')+
                       'top:'+(s.top=='auto'?'expression(((parseInt(this.parentNode.currentStyle.borderTopWidth)||0)*-1)+\'px\')':prop(s.top))+';'+
                       'left:'+(s.left=='auto'?'expression(((parseInt(this.parentNode.currentStyle.borderLeftWidth)||0)*-1)+\'px\')':prop(s.left))+';'+
                       'width:'+(s.width=='auto'?'expression(this.parentNode.offsetWidth+\'px\')':prop(s.width))+';'+
                       'height:'+(s.height=='auto'?'expression(this.parentNode.offsetHeight+\'px\')':prop(s.height))+';'+
                '"/>';
    return this.each(function() {
        if ( $(this).children('iframe.bgiframe').length === 0 )
            this.insertBefore( document.createElement(html), this.firstChild );
    });
} : function() { return this; });

// old alias
$.fn.bgIframe = $.fn.bgiframe;

function prop(n) {
    return n && n.constructor === Number ? n + 'px' : n;
}

})(jQuery);
/*
 * $Id: ajaxsubmit.js,v 1.2 2013/01/24 09:34:30 jiangdh Exp $
 * operamasks-ui omAjaxSubmit 2.0
 *
 * Copyright 2011, AUTHORS.txt (http://ui.operamasks.org/about)
 * Dual licensed under the MIT or LGPL Version 2 licenses.
 * http://ui.operamasks.org/license
 *
 * http://ui.operamasks.org/docs/
 */
(function($) {

/*
	Usage Note:
	-----------
	Do not use both omAjaxSubmit and ajaxForm on the same form.  These
	functions are intended to be exclusive.  Use omAjaxSubmit if you want
	to bind your own submit handler to the form.  For example,

	$(document).ready(function() {
		$('#myForm').bind('submit', function(e) {
			e.preventDefault(); // <-- important
			$(this).omAjaxSubmit({
				target: '#output'
			});
		});
	});

	Use ajaxForm when you want the plugin to manage all the event binding
	for you.  For example,

	$(document).ready(function() {
		$('#myForm').ajaxForm({
			target: '#output'
		});
	});

	When using ajaxForm, the omAjaxSubmit function will be invoked for you
	at the appropriate time.
*/

/**
 * @name omAjaxSubmit
 * @class
 * <div>
 * omAjaxSubmit() 提供使用ajax方式提交HTML form的一种机制。本插件会监听表单的submit事件，<br/>
 * 覆盖传统的submit事件监听器，而使用ajax方式来处理submit事件。在表单提交之前，本插件会收集<br/>
 * 所有的表单字段，并将之序列化后附加在ajax请求的数据域(data)中。支持所有标准的html可提交的<br/>
 * 表。元素。
 * </div><br/>
 * <b>事件回调</b><br/>
 * <div>
 * 通过丰富的配置参数，omAjaxSubmit可以高度自定制。同时提供多个事件回调函数，在每一次完整<br/>
 * 的表单提交的过程中，用户有时机能够对提交的请求进行修改。
 * </div>
 * <pre>beforeSerialize:</pre>
 * <div style="text-indent:2em;">在form序列化之前执行的回调函数。在获取所有form表单字段的值之前，该函数提供了一个操作form的时机</div>
 * <pre>beforeSubmit:</pre>
 * <div style="text-indent:2em;">在form被提交之前执行的回调函数。该函数提供了一个时机来执行预提交的逻辑，比如表单校验</div><br/>
 * <b>工具方法</b><br/>
 * <div>omAjaxSubmit还提供了一系列静态工具方法，用于方便地操作表单及其字段。</div>
 * <pre>$.fn.formToArray()</pre>
 * <div style="text-indent:2em;">将表单所有元素转换成key/value的数组，例如[{name:'username', value:'jack'},{name:'password', value:'secret'}]，<br/>
 * 注意:该数组将作为参数传递给beforeSubmit函数</div>
 * <pre>$.fn.formSerialize()</pre>
 * <div style="text-indent:2em;">将表单数据序列化成可提交的字符串，例如name1=value1&amp;name2=value2</div>
 * <pre>$.fn.fieldSerialize()</pre>
 * <div style="text-indent:2em;">将表单所有元素序列化成可提交的字符串，例如name1=value1&amp;name2=value2</div>
 * <pre>$.fn.fieldValue()</pre>
 * <div style="text-indent:2em;">获取当前元素(或元素数组)的值</div>
 * <pre>$.fieldValue(successful)</pre>
 * <div style="text-indent:2em;">静态工具方法，用于获取元素的值，参数successful的意义同上</div>
 * <pre>$.fn.clearForm()</pre>
 * <div style="text-indent:2em;">清空当前表单各个元素的值</div>
 * <pre>$.fn.clearFields()</pre>
 * <div style="text-indent:2em;">清空当前元素(或元素数组)的值</div>
 * <pre>$.fn.resetForm()</pre>
 * <div style="text-indent:2em;">重置当前表单各个元素的值</div>
 * <pre>$.fn.enable(b)</pre>
 * <div style="text-indent:2em;">设置当前元素(或元素数组)的使能状态</div>
 * <pre>$.fn.selected(selected)</pre>
 * <div style="text-indent:2em;">设置当前元素(或元素数组)的选中状态</div><br/>
 * <b>示例</b><br/>
 * <pre>
 *  $(document).ready(function() {
 *      $('#myForm').bind('submit', function(e) {
 *          e.preventDefault(); //阻止form默认的提交行为
 *              $(this).omAjaxSubmit(//使用ajax提交
 *                  {
 *                      target: '#output'
 *                  }
 *              );
 *      });
 *  });
 * 
 * </pre>
 * @constructor
 * @param options 标准config对象
 * @example
 * 	$('#formId').omAjaxSubmit({target: '#output'});
 */
$.fn.omAjaxSubmit = function(options) {
	// fast fail if nothing selected (http://dev.jquery.com/ticket/2752)
	if (!this.length) {
		log('omAjaxSubmit: skipping submit process - no element selected');
		return this;
	}
	
	var method, action, url, $form = this;

	if (typeof options == 'function') {
		options = { success: options };
	}

	method = this.attr('method');
	action = this.attr('action');

	url = (typeof action === 'string') ? $.trim(action) : '';
	url = url || window.location.href || '';
	if (url) {
		// clean url (don't include hash vaue)
		url = (url.match(/^([^#]+)/)||[])[1];
	}

	options = $.extend(true, {
        /**
         * 表单提交的url。
         * @name omAjaxSubmit#url
         * @type String
         * @default form的action属性值
         * @example
         * $('#formId').omAjaxSubmit({url : 'result.jsp'});
         */
		url:  url,
        /**
         * 当表单提交成功并取到响应时，执行的回调函数。
         * @name omAjaxSubmit#success
         * @param responseText 响应的文本。具体的取值根据options中的dataType有关，请参考dataType属性的说明文档。
         * @param statusText 响应的状态，在该回调中，常见的取值为success
         * @param xhr XMLHttpRequest对象
         * @param $form 经过jQuery包装的form对象
         * @event
         * @default 无
         * @example
         * //定义一个函数
         * function showResponse(responseText, statusText, xhr, $form) {
         *  alert('submit success!');
         * }
         * //提交成功取到响应时的回调函数
         * $('#formId').omAjaxSubmit({success: showResponse});
         */
		success: $.ajaxSettings.success,
        /**
         * 表单的提交方法，取值为：'GET' 或者 'POST'。
         * @name omAjaxSubmit#method
         * @type String
         * @default 'GET'
         * @example
         * $('#formId').omAjaxSubmit({method:'POST'});
         */
		method: method || 'GET',
        /**
         * iframe的src属性值，该属性在页面中有iframe的时候才用得到，通常此时form中有文件需要上传。<br/>
         * 默认值是about:blank ，如果当前页面地址使用 https 协议，则该值为javascript:false
         * @blocked
         */
		iframeSrc: /^https/i.test(window.location.href || '') ? 'javascript:false' : 'about:blank'
	}, options);

	// hook for manipulating the form data before it is extracted;
	// convenient for use with rich editors like tinyMCE or FCKEditor
	var veto = {};
	this.trigger('form-pre-serialize', [this, options, veto]);
	if (veto.veto) {
		log('omAjaxSubmit: submit vetoed via form-pre-serialize trigger');
		return this;
	}

    /**
     * 在form序列化之前执行的回调函数。在获取form表单元素的值之前，该函数提供了一个操作form的时机。<br/>
     * 该函数接受2个参数<br/>
     * @name omAjaxSubmit#beforeSerialize
     * @event
     * @param $form form对应的jQuery对象
     * @param options 传给ajaxSubmit的options对象
     * @return false 取消form的提交
     * @example
     * beforeSerialize: function($form, options) { 
     *     // return false to cancel submit                  
     * }
     */
	// provide opportunity to alter form data before it is serialized
	if (options.beforeSerialize && options.beforeSerialize(this, options) === false) {
		log('omAjaxSubmit: submit aborted via beforeSerialize callback');
		return this;
	}

    /**
     * 
     * 是否以严格的语义化顺序提交form表单元素。同时，设置了该属性会忽略表单中的image标签。<br/>
     * 该属性一般不用设置(默认为false)。只有当你的服务器对semantic order有严格要求，<br/>
     * 并且你的表单中含有image时，你才需要设置它为true<br/>
     * @blocked
     */
	var n,v,a = this.formToArray(options.semantic);
    /**
     * ajax提交中的附加数据，以JSON的形式组成(key/value)。如果value是数组，将会被展开;如果value是函数，将会被求值。
     * @type JSON
     * @name omAjaxSubmit#data
     * @default 无
     * @example
     * data: { key1: 'value1', key2: 'value2' }
     */
	if (options.data) {
		options.extraData = options.data;
		for (n in options.data) {
			if(options.data[n] instanceof Array) {
				for (var k in options.data[n]) {
					a.push( { name: n, value: options.data[n][k] } );
				}
			}
			else {
				v = options.data[n];
				v = $.isFunction(v) ? v() : v; // if value is fn, invoke it
				a.push( { name: n, value: v } );
			}
		}
	}

    /**
     * 在form被提交之前执行的回调函数。该函数提供了一个时机来执行预提交的逻辑，或者可以用来进行校验表单元素。<br/>
     * 该函数接受3个参数:arr, $form, options。<br/>
     * 若函数返回false，则会取消form的提交。<br/>
     * @name omAjaxSubmit#beforeSubmit
     * @type Function
     * @event
     * @param arr 一个数组，包含form所有字段的key/value值，例如: [{key:value},{key1:value1},{key2:value2}]
     * @param $form form对应的jQuery对象
     * @param options 传递给ajaxSubmit的options参数
     * @return false 取消提交表单
     * @example
     * beforeSubmit: function(arr, $form, options) { 
     *     // The array of form data takes the following form: 
     *     // [ { name: 'username', value: 'jresig' }, { name: 'password', value: 'secret' } ] 
     *     // return false to cancel submit                  
     * }
     */
	// give pre-submit callback an opportunity to abort the submit
	if (options.beforeSubmit && options.beforeSubmit(a, this, options) === false) {
		log('omAjaxSubmit: submit aborted via beforeSubmit callback');
		return this;
	}

	// fire vetoable 'validate' event
	this.trigger('form-submit-validate', [a, this, options, veto]);
	if (veto.veto) {
		log('omAjaxSubmit: submit vetoed via form-submit-validate trigger');
		return this;
	}

	var q = $.param(a);

	if (options.method.toUpperCase() == 'GET') {
		options.url += (options.url.indexOf('?') >= 0 ? '&' : '?') + q;
		options.data = null;  // data is null for 'get'
	}
	else {
		options.data = q; // data is the query string for 'post'
	}

    var callbacks = [];
    /**
     * 在form成功提交后，是否将form字段重置
     * @name omAjaxSubmit#resetForm
     * @type Boolean
     * @default false
     * @example
     * //提交后重置表单字段
     * $('#formId').omAjaxSubmit({resetForm: true});
     */
	if (options.resetForm) {
		callbacks.push(function() { $form.resetForm(); });
	}
    /**
     * 在form成功提交后，是否将form字段清空。<br/>
     * @name omAjaxSubmit#clearForm
     * @type Boolean
     * @default false
     * @example
     * $('#formId').omAjaxSubmit({clearForm: true});
     */
	if (options.clearForm) {
		callbacks.push(function() { $form.clearForm(); });
	}

    /**
     * 响应的数据格式，可选的取值为'xml'， 'script'， 'json'或者null。该选项表明了响应将要被如何处理。<br/>
     * 与jQuery.httpData一一对应，其各种取值情况处理如下：<br/>
     * <pre>
     *      'xml':	响应将会被认为是xml格式的，并作为第一个参数传递给success回调函数
     *      'json':	响应将会被认为是json格式的，其将会被求值，结果将会作为第一个参数传递给success回调函数
     *      'script':响应将会被认为是js脚本，其将在全局上下文中被执行
     * </pre>
     * @name omAjaxSubmit#dataType
     * @type String
     * @default 无
     * @example
     * $('#formId').omAjaxSubmit({dataType : 'json'}); 
     */
    /**
     * 指定了一个更新区域，该区域将会被ajax响应更新。<br/>
     * 该值可以是DOM元素，jQuery对象，或者一个可以被jQuery选择到的选择器。
     * @name omAjaxSubmit#target
     * @type DOM, jQuery, or String
     * @default 无
     * @example
     * $('#formId').omAjaxSubmit({target : '#targetDivId'});
     */
	// perform a load on the target only if dataType is not provided
	if (!options.dataType && options.target) {
		var oldSuccess = options.success || function(){};
		callbacks.push(function(data) {
            /**
             * 可选配置，是否替换target指定的区域。<br/>
             * 设为true将会整体替换target对应的DOM节点，设为false将只会替换节点的内容。<br/>
             * @name omAjaxSubmit#replaceTarget
             * @type Boolean 
             * @default false
             * @example
             * $('#formId').omAjaxSubmit({replaceTarget : true});
             */
			var fn = options.replaceTarget ? 'replaceWith' : 'html';
			$(options.target)[fn](data).each(oldSuccess, arguments);
		});
	}
	else if (options.success) {
		callbacks.push(options.success);
	}

	options.success = function(data, status, xhr) { // jQuery 1.4+ passes xhr as 3rd arg
		var context = options.context || options;   // jQuery 1.4+ supports scope context 
		for (var i=0, max=callbacks.length; i < max; i++) {
			callbacks[i].apply(context, [data, status, xhr || $form, $form]);
		}
	};

	// are there files to upload?
	var fileInputs = $('input:file', this).length > 0;
	var mp = 'multipart/form-data';
	var multipart = ($form.attr('enctype') == mp || $form.attr('encoding') == mp);

    /**
     * 是否总是将form的响应指向一个iframe，该属性在有文件上传的情况下有用。
     * @blocked
     */
	// options.iframe allows user to force iframe mode
	// 06-NOV-09: now defaulting to iframe mode if file input is detected
   if (options.iframe !== false && (fileInputs || options.iframe || multipart)) {
	   // hack to fix Safari hang (thanks to Tim Molendijk for this)
	   // see:  http://groups.google.com/group/jquery-dev/browse_thread/thread/36395b7ab510dd5d
	   if (options.closeKeepAlive) {
		   $.get(options.closeKeepAlive, function() { fileUpload(a); });
		}
	   else {
		   fileUpload(a);
		}
   }
   else {
		// IE7 massage (see issue 57)
		if ($.browser.msie && method == 'get') { 
			var ieMeth = $form[0].getAttribute('method');
			if (typeof ieMeth === 'string')
				options.method = ieMeth;
		}
		options.type = options.method;
		$.ajax(options);
   }

	// fire 'notify' event
	this.trigger('form-submit-notify', [this, options]);
	return this;


	// private function for handling file uploads (hat tip to YAHOO!)
	function fileUpload(a) {
		var form = $form[0], el, i, s, g, id, $io, io, xhr, sub, n, timedOut, timeoutHandle;
        var useProp = !!$.fn.prop;

        if (a) {
        	// ensure that every serialized input is still enabled
          	for (i=0; i < a.length; i++) {
                el = $(form[a[i].name]);
                el[ useProp ? 'prop' : 'attr' ]('disabled', false);
          	}
        }

		if ($(':input[name=submit],:input[id=submit]', form).length) {
			// if there is an input with a name or id of 'submit' then we won't be
			// able to invoke the submit fn on the form (at least not x-browser)
			alert('Error: Form elements must not have name or id of "submit".');
			return;
		}
		
		s = $.extend(true, {}, $.ajaxSettings, options);
		s.context = s.context || s;
		id = 'jqFormIO' + (new Date().getTime());
		/**
		 * 指定一个iframe元素。当本插件处理有文件上传的form时，一般会临时创建一个隐藏的iframe来接收响应。<br/>
		 * 配置该属性，用户可以使用一个已存在的iframe，而不是使用临时iframe。<br/>
		 * 注意使用该属性后，本插件不会再去尝试处理服务器的响应。<br/>
		 * @blocked
         */
		if (s.iframeTarget) {
			$io = $(s.iframeTarget);
			n = $io.attr('name');
			if (n == null)
			 	$io.attr('name', id);
			else
				id = n;
		}
		else {
			$io = $('<iframe name="' + id + '" src="'+ s.iframeSrc +'" />');
			$io.css({ position: 'absolute', top: '-1000px', left: '-1000px' });
		}
		io = $io[0];


		xhr = { // mock object
			aborted: 0,
			responseText: null,
			responseXML: null,
			status: 0,
			statusText: 'n/a',
			getAllResponseHeaders: function() {},
			getResponseHeader: function() {},
			setRequestHeader: function() {},
			abort: function(status) {
				var e = (status === 'timeout' ? 'timeout' : 'aborted');
				log('aborting upload... ' + e);
				this.aborted = 1;
				$io.attr('src', s.iframeSrc); // abort op in progress
				xhr.error = e;
				s.error && s.error.call(s.context, xhr, e, status);
				g && $.event.trigger("ajaxError", [xhr, s, e]);
				s.complete && s.complete.call(s.context, xhr, e);
			}
		};

		g = s.global;
		// trigger ajax global events so that activity/block indicators work like normal
		if (g && ! $.active++) {
			$.event.trigger("ajaxStart");
		}
		if (g) {
			$.event.trigger("ajaxSend", [xhr, s]);
		}

		if (s.beforeSend && s.beforeSend.call(s.context, xhr, s) === false) {
			if (s.global) {
				$.active--;
			}
			return;
		}
		if (xhr.aborted) {
			return;
		}

		// add submitting element to data if we know it
		sub = form.clk;
		if (sub) {
			n = sub.name;
			if (n && !sub.disabled) {
				s.extraData = s.extraData || {};
				s.extraData[n] = sub.value;
				if (sub.type == "image") {
					s.extraData[n+'.x'] = form.clk_x;
					s.extraData[n+'.y'] = form.clk_y;
				}
			}
		}
		
		var CLIENT_TIMEOUT_ABORT = 1;
		var SERVER_ABORT = 2;

		function getDoc(frame) {
			var doc = frame.contentWindow ? frame.contentWindow.document : frame.contentDocument ? frame.contentDocument : frame.document;
			return doc;
		}
		
		// take a breath so that pending repaints get some cpu time before the upload starts
		function doSubmit() {
			// make sure form attrs are set
			var t = $form.attr('target'), a = $form.attr('action');

			// update form attrs in IE friendly way
			form.setAttribute('target',id);
			if (!method) {
				form.setAttribute('method', 'POST');
			}
			if (a != s.url) {
				form.setAttribute('action', s.url);
			}

			// ie borks in some cases when setting encoding
			if (! s.skipEncodingOverride && (!method || /post/i.test(method))) {
				$form.attr({
					encoding: 'multipart/form-data',
					enctype:  'multipart/form-data'
				});
			}

			// support timout
			if (s.timeout) {
				timeoutHandle = setTimeout(function() { timedOut = true; cb(CLIENT_TIMEOUT_ABORT); }, s.timeout);
			}
			
			// look for server aborts
			function checkState() {
				try {
					var state = getDoc(io).readyState;
					log('state = ' + state);
					if (state.toLowerCase() == 'uninitialized')
						setTimeout(checkState,50);
				}
				catch(e) {
					log('Server abort: ' , e, ' (', e.name, ')');
					cb(SERVER_ABORT);
					timeoutHandle && clearTimeout(timeoutHandle);
					timeoutHandle = undefined;
				}
			}

			// add "extra" data to form if provided in options
			var extraInputs = [];
			try {
				if (s.extraData) {
					for (var n in s.extraData) {
						extraInputs.push(
							$('<input type="hidden" name="'+n+'" />').attr('value',s.extraData[n])
								.appendTo(form)[0]);
					}
				}

				if (!s.iframeTarget) {
					// add iframe to doc and submit the form
					$io.appendTo('body');
	                io.attachEvent ? io.attachEvent('onload', cb) : io.addEventListener('load', cb, false);
				}
				setTimeout(checkState,15);
				form.submit();
			}
			finally {
				// reset attrs and remove "extra" input elements
				form.setAttribute('action',a);
				if(t) {
					form.setAttribute('target', t);
				} else {
					$form.removeAttr('target');
				}
				$(extraInputs).remove();
			}
		}
        /**
         * 强制同步，如果设置了该属性，在form提交时将立即进行文件上传，否则在提交表单后会延迟10毫秒再进行文件上传。<br/>
         * 在延迟的这一短暂时间里，浏览器有机会更新DOM结构，比如想要向用户展示"请稍后..."的提示。<br/>
         * 显示这些的提示是需要时间来更新DOM结构的。暂停这一会儿时间，再真正提交表单，可以增加易用性。<br/>
         * @blocked
         */
		if (s.forceSync) {
			doSubmit();
		}
		else {
			setTimeout(doSubmit, 10); // this lets dom updates render
		}

		var data, doc, domCheckCount = 50, callbackProcessed;

		function cb(e) {
			if (xhr.aborted || callbackProcessed) {
				return;
			}
			try {
				doc = getDoc(io);
			}
			catch(ex) {
				log('cannot access response document: ', ex);
				e = SERVER_ABORT;
			}
			if (e === CLIENT_TIMEOUT_ABORT && xhr) {
				xhr.abort('timeout');
				return;
			}
			else if (e == SERVER_ABORT && xhr) {
				xhr.abort('server abort');
				return;
			}

			if (!doc || doc.location.href == s.iframeSrc) {
				// response not received yet
				if (!timedOut)
					return;
			}
            io.detachEvent ? io.detachEvent('onload', cb) : io.removeEventListener('load', cb, false);

			var status = 'success', errMsg;
			try {
				if (timedOut) {
					throw 'timeout';
				}

				var isXml = s.dataType == 'xml' || doc.XMLDocument || $.isXMLDoc(doc);
				log('isXml='+isXml);
				if (!isXml && window.opera && (doc.body == null || doc.body.innerHTML == '')) {
					if (--domCheckCount) {
						// in some browsers (Opera) the iframe DOM is not always traversable when
						// the onload callback fires, so we loop a bit to accommodate
						log('requeing onLoad callback, DOM not available');
						setTimeout(cb, 250);
						return;
					}
					// let this fall through because server response could be an empty document
					//log('Could not access iframe DOM after mutiple tries.');
					//throw 'DOMException: not available';
				}

				//log('response detected');
                var docRoot = doc.body ? doc.body : doc.documentElement;
                xhr.responseText = docRoot ? docRoot.innerHTML : null;
				xhr.responseXML = doc.XMLDocument ? doc.XMLDocument : doc;
				if (isXml)
					s.dataType = 'xml';
				xhr.getResponseHeader = function(header){
					var headers = {'content-type': s.dataType};
					return headers[header];
				};
                // support for XHR 'status' & 'statusText' emulation :
                if (docRoot) {
                    xhr.status = Number( docRoot.getAttribute('status') ) || xhr.status;
                    xhr.statusText = docRoot.getAttribute('statusText') || xhr.statusText;
                }

				var dt = s.dataType || '';
				var scr = /(json|script|text)/.test(dt.toLowerCase());
				if (scr || s.textarea) {
					// see if user embedded response in textarea
					var ta = doc.getElementsByTagName('textarea')[0];
					if (ta) {
						xhr.responseText = ta.value;
                        // support for XHR 'status' & 'statusText' emulation :
                        xhr.status = Number( ta.getAttribute('status') ) || xhr.status;
                        xhr.statusText = ta.getAttribute('statusText') || xhr.statusText;
					}
					else if (scr) {
						// account for browsers injecting pre around json response
						var pre = doc.getElementsByTagName('pre')[0];
						var b = doc.getElementsByTagName('body')[0];
						if (pre) {
							xhr.responseText = pre.textContent ? pre.textContent : pre.innerHTML;
						}
						else if (b) {
							xhr.responseText = b.innerHTML;
						}
					}
				}
				else if (s.dataType == 'xml' && !xhr.responseXML && xhr.responseText != null) {
					xhr.responseXML = toXml(xhr.responseText);
				}

                try {
                    data = httpData(xhr, s.dataType, s);
                }
                catch (e) {
                    status = 'parsererror';
                    xhr.error = errMsg = (e || status);
                }
			}
			catch (e) {
				log('error caught: ',e);
				status = 'error';
                xhr.error = errMsg = (e || status);
			}

			if (xhr.aborted) {
				log('upload aborted');
				status = null;
			}

            if (xhr.status) { // we've set xhr.status
                status = (xhr.status >= 200 && xhr.status < 300 || xhr.status === 304) ? 'success' : 'error';
            }

			// ordering of these callbacks/triggers is odd, but that's how $.ajax does it
			if (status === 'success') {
				s.success && s.success.call(s.context, data, 'success', xhr);
				g && $.event.trigger("ajaxSuccess", [xhr, s]);
			}
            else if (status) {
				if (errMsg == undefined)
					errMsg = xhr.statusText;
				s.error && s.error.call(s.context, xhr, status, errMsg);
				g && $.event.trigger("ajaxError", [xhr, s, errMsg]);
            }

			g && $.event.trigger("ajaxComplete", [xhr, s]);

			if (g && ! --$.active) {
				$.event.trigger("ajaxStop");
			}

			s.complete && s.complete.call(s.context, xhr, status);

			callbackProcessed = true;
			if (s.timeout)
				clearTimeout(timeoutHandle);

			// clean up
			setTimeout(function() {
				if (!s.iframeTarget)
					$io.remove();
				xhr.responseXML = null;
			}, 100);
		}

		var toXml = $.parseXML || function(s, doc) { // use parseXML if available (jQuery 1.5+)
			if (window.ActiveXObject) {
				doc = new ActiveXObject('Microsoft.XMLDOM');
				doc.async = 'false';
				doc.loadXML(s);
			}
			else {
				doc = (new DOMParser()).parseFromString(s, 'text/xml');
			}
			return (doc && doc.documentElement && doc.documentElement.nodeName != 'parsererror') ? doc : null;
		};
		var parseJSON = $.parseJSON || function(s) {
			return window['eval']('(' + s + ')');
		};

		var httpData = function( xhr, type, s ) { // mostly lifted from jq1.4.4

			var ct = xhr.getResponseHeader('content-type') || '',
				xml = type === 'xml' || !type && ct.indexOf('xml') >= 0,
				data = xml ? xhr.responseXML : xhr.responseText;

			if (xml && data.documentElement.nodeName === 'parsererror') {
				$.error && $.error('parsererror');
			}
			if (s && s.dataFilter) {
				data = s.dataFilter(data, type);
			}
			if (typeof data === 'string') {
				if (type === 'json' || !type && ct.indexOf('json') >= 0) {
					data = parseJSON(data);
				} else if (type === "script" || !type && ct.indexOf("javascript") >= 0) {
					$.globalEval(data);
				}
			}
			return data;
		};
	}
};

/**
 * ajaxForm() provides a mechanism for fully automating form submission.
 *
 * The advantages of using this method instead of omAjaxSubmit() are:
 *
 * 1: This method will include coordinates for <input type="image" /> elements (if the element
 *	is used to submit the form).
 * 2. This method will include the submit element's name/value data (for the element that was
 *	used to submit the form).
 * 3. This method binds the submit() method to the form for you.
 *
 * The options argument for ajaxForm works exactly as it does for omAjaxSubmit.  ajaxForm merely
 * passes the options argument along after properly binding events for submit elements and
 * the form itself.
 */
$.fn.ajaxForm = function(options) {
	// in jQuery 1.3+ we can fix mistakes with the ready state
	if (this.length === 0) {
		var o = { s: this.selector, c: this.context };
		if (!$.isReady && o.s) {
			log('DOM not ready, queuing ajaxForm');
			$(function() {
				$(o.s,o.c).ajaxForm(options);
			});
			return this;
		}
		// is your DOM ready?  http://docs.jquery.com/Tutorials:Introducing_$(document).ready()
		log('terminating; zero elements found by selector' + ($.isReady ? '' : ' (DOM not ready)'));
		return this;
	}

	return this.ajaxFormUnbind().bind('submit.form-plugin', function(e) {
		if (!e.isDefaultPrevented()) { // if event has been canceled, don't proceed
			e.preventDefault();
			$(this).omAjaxSubmit(options);
		}
	}).bind('click.form-plugin', function(e) {
		var target = e.target;
		var $el = $(target);
		if (!($el.is(":submit,input:image"))) {
			// is this a child element of the submit el?  (ex: a span within a button)
			var t = $el.closest(':submit');
			if (t.length == 0) {
				return;
			}
			target = t[0];
		}
		var form = this;
		form.clk = target;
		if (target.type == 'image') {
			if (e.offsetX != undefined) {
				form.clk_x = e.offsetX;
				form.clk_y = e.offsetY;
			} else if (typeof $.fn.offset == 'function') { // try to use dimensions plugin
				var offset = $el.offset();
				form.clk_x = e.pageX - offset.left;
				form.clk_y = e.pageY - offset.top;
			} else {
				form.clk_x = e.pageX - target.offsetLeft;
				form.clk_y = e.pageY - target.offsetTop;
			}
		}
		// clear form vars
		setTimeout(function() { form.clk = form.clk_x = form.clk_y = null; }, 100);
	});
};

// ajaxFormUnbind unbinds the event handlers that were bound by ajaxForm
$.fn.ajaxFormUnbind = function() {
	return this.unbind('submit.form-plugin click.form-plugin');
};

/**
 * formToArray() gathers form element data into an array of objects that can
 * be passed to any of the following ajax functions: $.get, $.post, or load.
 * Each object in the array has both a 'name' and 'value' property.  An example of
 * an array for a simple login form might be:
 *
 * [ { name: 'username', value: 'jresig' }, { name: 'password', value: 'secret' } ]
 *
 * It is this array that is passed to pre-submit callback functions provided to the
 * omAjaxSubmit() and ajaxForm() methods.
 */
$.fn.formToArray = function(semantic) {
	var a = [];
	if (this.length === 0) {
		return a;
	}

	var form = this[0];
	var els = semantic ? form.getElementsByTagName('*') : form.elements;
	if (!els) {
		return a;
	}

	var i,j,n,v,el,max,jmax;
	for(i=0, max=els.length; i < max; i++) {
		el = els[i];
		n = el.name;
		if (!n) {
			continue;
		}

		if (semantic && form.clk && el.type == "image") {
			// handle image inputs on the fly when semantic == true
			if(!el.disabled && form.clk == el) {
				a.push({name: n, value: $(el).val()});
				a.push({name: n+'.x', value: form.clk_x}, {name: n+'.y', value: form.clk_y});
			}
			continue;
		}

		v = $.fieldValue(el, true);
		if (v && v.constructor == Array) {
			for(j=0, jmax=v.length; j < jmax; j++) {
				a.push({name: n, value: v[j]});
			}
		}
		else if (v !== null && typeof v != 'undefined') {
			a.push({name: n, value: v});
		}
	}

	if (!semantic && form.clk) {
		// input type=='image' are not found in elements array! handle it here
		var $input = $(form.clk), input = $input[0];
		n = input.name;
		if (n && !input.disabled && input.type == 'image') {
			a.push({name: n, value: $input.val()});
			a.push({name: n+'.x', value: form.clk_x}, {name: n+'.y', value: form.clk_y});
		}
	}
	return a;
};

/**
 * Serializes form data into a 'submittable' string. This method will return a string
 * in the format: name1=value1&amp;name2=value2
 */
$.fn.formSerialize = function(semantic) {
	//hand off to jQuery.param for proper encoding
	return $.param(this.formToArray(semantic));
};

/**
 * Serializes all field elements in the jQuery object into a query string.
 * This method will return a string in the format: name1=value1&amp;name2=value2
 */
$.fn.fieldSerialize = function(successful) {
	var a = [];
	this.each(function() {
		var n = this.name;
		if (!n) {
			return;
		}
		var v = $.fieldValue(this, successful);
		if (v && v.constructor == Array) {
			for (var i=0,max=v.length; i < max; i++) {
				a.push({name: n, value: v[i]});
			}
		}
		else if (v !== null && typeof v != 'undefined') {
			a.push({name: this.name, value: v});
		}
	});
	//hand off to jQuery.param for proper encoding
	return $.param(a);
};

/**
 * Returns the value(s) of the element in the matched set.  For example, consider the following form:
 *
 *  <form><fieldset>
 *	  <input name="A" type="text" />
 *	  <input name="A" type="text" />
 *	  <input name="B" type="checkbox" value="B1" />
 *	  <input name="B" type="checkbox" value="B2"/>
 *	  <input name="C" type="radio" value="C1" />
 *	  <input name="C" type="radio" value="C2" />
 *  </fieldset></form>
 *
 *  var v = $(':text').fieldValue();
 *  // if no values are entered into the text inputs
 *  v == ['','']
 *  // if values entered into the text inputs are 'foo' and 'bar'
 *  v == ['foo','bar']
 *
 *  var v = $(':checkbox').fieldValue();
 *  // if neither checkbox is checked
 *  v === undefined
 *  // if both checkboxes are checked
 *  v == ['B1', 'B2']
 *
 *  var v = $(':radio').fieldValue();
 *  // if neither radio is checked
 *  v === undefined
 *  // if first radio is checked
 *  v == ['C1']
 *
 * The successful argument controls whether or not the field element must be 'successful'
 * (per http://www.w3.org/TR/html4/interact/forms.html#successful-controls).
 * The default value of the successful argument is true.  If this value is false the value(s)
 * for each element is returned.
 *
 * Note: This method *always* returns an array.  If no valid value can be determined the
 *	   array will be empty, otherwise it will contain one or more values.
 */
$.fn.fieldValue = function(successful) {
	for (var val=[], i=0, max=this.length; i < max; i++) {
		var el = this[i];
		var v = $.fieldValue(el, successful);
		if (v === null || typeof v == 'undefined' || (v.constructor == Array && !v.length)) {
			continue;
		}
		v.constructor == Array ? $.merge(val, v) : val.push(v);
	}
	return val;
};

/**
 * Returns the value of the field element.
 */
$.fieldValue = function(el, successful) {
	var n = el.name, t = el.type, tag = el.tagName.toLowerCase();
	if (successful === undefined) {
		successful = true;
	}

	if (successful && (!n || el.disabled || t == 'reset' || t == 'button' ||
		(t == 'checkbox' || t == 'radio') && !el.checked ||
		(t == 'submit' || t == 'image') && el.form && el.form.clk != el ||
		tag == 'select' && el.selectedIndex == -1)) {
			return null;
	}

	if (tag == 'select') {
		var index = el.selectedIndex;
		if (index < 0) {
			return null;
		}
		var a = [], ops = el.options;
		var one = (t == 'select-one');
		var max = (one ? index+1 : ops.length);
		for(var i=(one ? index : 0); i < max; i++) {
			var op = ops[i];
			if (op.selected) {
				var v = op.value;
				if (!v) { // extra pain for IE...
					v = (op.attributes && op.attributes['value'] && !(op.attributes['value'].specified)) ? op.text : op.value;
				}
				if (one) {
					return v;
				}
				a.push(v);
			}
		}
		return a;
	}
	return $(el).val();
};

/**
 * Clears the form data.  Takes the following actions on the form's input fields:
 *  - input text fields will have their 'value' property set to the empty string
 *  - select elements will have their 'selectedIndex' property set to -1
 *  - checkbox and radio inputs will have their 'checked' property set to false
 *  - inputs of type submit, button, reset, and hidden will *not* be effected
 *  - button elements will *not* be effected
 */
$.fn.clearForm = function() {
	return this.each(function() {
		$('input,select,textarea', this).clearFields();
	});
};

/**
 * Clears the selected form elements.
 */
$.fn.clearFields = $.fn.clearInputs = function() {
	var re = /^(?:color|date|datetime|email|month|number|password|range|search|tel|text|time|url|week)$/i; // 'hidden' is not in this list
	return this.each(function() {
		var t = this.type, tag = this.tagName.toLowerCase();
		if (re.test(t) || tag == 'textarea') {
			this.value = '';
		}
		else if (t == 'checkbox' || t == 'radio') {
			this.checked = false;
		}
		else if (tag == 'select') {
			this.selectedIndex = -1;
		}
	});
};

/**
 * Resets the form data.  Causes all form elements to be reset to their original value.
 */
$.fn.resetForm = function() {
	return this.each(function() {
		// guard against an input with the name of 'reset'
		// note that IE reports the reset function as an 'object'
		if (typeof this.reset == 'function' || (typeof this.reset == 'object' && !this.reset.nodeType)) {
			this.reset();
		}
	});
};


/**
 * Checks/unchecks any matching checkboxes or radio buttons and
 * selects/deselects and matching option elements.
 */
$.fn.selected = function(select) {
	if (select === undefined) {
		select = true;
	}
	return this.each(function() {
		var t = this.type;
		if (t == 'checkbox' || t == 'radio') {
			this.checked = select;
		}
		else if (this.tagName.toLowerCase() == 'option') {
			var $sel = $(this).parent('select');
			if (select && $sel[0] && $sel[0].type == 'select-one') {
				// deselect all other options
				$sel.find('option').selected(false);
			}
			this.selected = select;
		}
	});
};

// helper fn for console logging
function log() {
	var msg = '[jquery.form] ' + Array.prototype.join.call(arguments,'');
	if (window.console && window.console.log) {
		window.console.log(msg);
	}
	else if (window.opera && window.opera.postError) {
		window.opera.postError(msg);
	}
};

})(jQuery);
