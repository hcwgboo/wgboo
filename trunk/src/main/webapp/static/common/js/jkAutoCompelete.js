
/**
 * 
 * 获取楼盘信息
 */
function acProjectFn(url,selectFn) {
	return baseAutoCompelete(url, null, function(item) {
		return {
			value: item.name,
			id: item.id,
		}
	}, function(e, ui) {
		selectFn(e, ui);
	});
}

/**
 * 总代部门查询
 * @param url
 * @param selectFn
 * @returns
 */
function acGaOrganizationFun(url,selectFn){
	return baseAutoCompelete(url, null, function(item) {
		return {
			value: item.name,
			id: item.id,
		}
	}, function(e, ui) {
		selectFn(e, ui);
	});
}

/**
 * 公共autoCompelete
 */

function baseAutoCompelete(apiurl, paramFn, mapper, selectFn) {
	return {
		source: function(request, response) {
			var _data = {
				keyword: $.trim(request.term)
			}
			var param = $.extend(true, _data, (paramFn ? paramFn : {}));
			$.ajax({
				type: "get",
				url: apiurl,
				dataType: "json",
				data: $.extend(true, _data, (paramFn ? paramFn : {})),
				success: function(data) {
					if(!mapper) {
						response([{
							label: '找不到数据转换方法',
							value: '',
							isNull: true
						}]);
						return;
					}
					data = data.total ? data.rows : data;
					if(data && data.length > 0) {
						response($.map(data, mapper));
					} else {
						var p = mapper({});
						if(p != null && p.nonTip == true) {
							response();
						} else {
							response([{
								label: '找不到 ' + $.trim(request.term) + ' 的相关数据',
								value: '',
								isNull: true
							}]);
						}
					}
				}
			});
		},
		minLength: 0,
		autoFocus: true,
		select: function(e, ui) {
			selectFn(e, ui);
			return false;
		}
	}
}