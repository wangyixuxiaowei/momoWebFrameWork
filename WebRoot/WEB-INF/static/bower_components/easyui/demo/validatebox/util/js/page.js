$.fn.pagination.defaults = {
    total: 1,
    pageSize: 10,
    pageNumber: 1,
    pageList: [10, 20, 30, 50],
    loading: false,
    buttons: null,
    showPageList: true,
    showRefresh: true,
    links: 10,
    layout: ["list", "sep", "first", "prev", "sep", "manual", "sep", "next", "last", "sep", "refresh"],
    onSelectPage: function(_a7, _a8) {},
    onBeforeRefresh: function(_a9, _aa) {},
    onRefresh: function(_ab, _ac) {},
    onChangePageSize: function(_ad) {},
    beforePageText: "Page",
    afterPageText: "of {pages}",
    displayMsg: "Displaying {from} to {to} of {total} items",
    nav: {
        first: {
            iconCls: "pagination-first",
            handler: function() {
                var _ae = $(this).pagination("options");
                if (_ae.pageNumber > 1) {
                    $(this).pagination("select", 1);
                }
            }
        },
        prev: {
            iconCls: "pagination-prev",
            handler: function() {
                var _af = $(this).pagination("options");
                if (_af.pageNumber > 1) {
                    $(this).pagination("select", _af.pageNumber - 1);
                }
            }
        },
        next: {
            iconCls: "pagination-next",
            handler: function() {
                var _b0 = $(this).pagination("options");
                var _b1 = Math.ceil(_b0.total / _b0.pageSize);
                if (_b0.pageNumber < _b1) {
                    $(this).pagination("select", _b0.pageNumber + 1);
                }
            }
        },
        last: {
            iconCls: "pagination-last",
            handler: function() {
                var _b2 = $(this).pagination("options");
                var _b3 = Math.ceil(_b2.total / _b2.pageSize);
                if (_b2.pageNumber < _b3) {
                    $(this).pagination("select", _b3);
                }
            }
        },
        refresh: {
            iconCls: "pagination-refresh",
            handler: function() {
                var _b4 = $(this).pagination("options");
                if (_b4.onBeforeRefresh.call(this, _b4.pageNumber, _b4.pageSize) != false) {
                    $(this).pagination("select", _b4.pageNumber);
                    _b4.onRefresh.call(this, _b4.pageNumber, _b4.pageSize);
                }
            }
        }
    }
};