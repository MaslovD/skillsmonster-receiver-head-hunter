webpackJsonp(["mail.module"],{

/***/ "../../../../../src/app/pages/mail/mail-compose/mail-compose.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"card-body compose\">\n   \n    <form method=\"get\" action=\"#\">\n        <div class=\"form-group\">\n            <input type=\"text\" class=\"form-control\" placeholder=\"To\">\n        </div>\n        <div class=\"form-group\">\n            <input type=\"text\" class=\"form-control\" placeholder=\"Cc / Bcc\">\n        </div>\n        <div class=\"form-group\">\n            <input type=\"text\" class=\"form-control\" placeholder=\"Subject\">\n        </div>\n        <div class=\"form-group\">\n            <textarea class=\"form-control\" placeholder=\"Message\" rows=\"10\"></textarea>\n        </div>\n        <div class=\"form-group pull-right\">\n            <a [routerLink]=\"['../mail-list/inbox']\" class=\"btn btn-secondary\">Cancel</a>\n            <button class=\"btn btn-main\" type=\"submit\">Send</button>            \n        </div>\n    </form>\n\n </div>\n \n"

/***/ }),

/***/ "../../../../../src/app/pages/mail/mail-compose/mail-compose.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MailComposeComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var MailComposeComponent = (function () {
    function MailComposeComponent() {
    }
    MailComposeComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'az-mail-compose',
            encapsulation: __WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewEncapsulation"].None,
            template: __webpack_require__("../../../../../src/app/pages/mail/mail-compose/mail-compose.component.html")
        })
    ], MailComposeComponent);
    return MailComposeComponent;
}());

//# sourceMappingURL=mail-compose.component.js.map

/***/ }),

/***/ "../../../../../src/app/pages/mail/mail-detail/mail-detail.component.html":
/***/ (function(module, exports) {

module.exports = "<div *ngIf=\"mail\">  \n    <div class=\"card-body\">\n        <div class=\"detail-header clearfix\">\n            <a href=\"#\" class=\"text-muted pull-right\"><i class=\"fa fa-print\"></i></a>\n            <h4 class=\"pull-left\">{{mail.subject}}</h4>\n        </div>\n        <hr/>\n\n        <div class=\"detail-content bottom-15 clearfix\">\n            <div class=\"avatar pull-left\">\n                <i class=\"fa fa-user\" aria-hidden=\"true\"></i>\n            </div>\n            <div class=\"pull-left\">\n                <strong>{{mail.sender}}</strong>\n                <span *ngIf=\"mail.senderMail\" class=\"email\">&lt;{{mail.senderMail}}&gt;</span><br>\n                <span class=\"receiver\">to me</span>\n            </div>\n\n            <div class=\"pull-right\">\n                <span class=\"mail-date\"> {{mail.date}} </span>\n                <div class=\"btn-group\">\n                    <button class=\"btn btn-sm btn-secondary\"><i class=\"fa fa-reply\"></i> Reply</button>\n                    <button class=\"btn btn-sm btn-secondary dropdown-toggle\" data-toggle=\"dropdown\"></button>\n                    <ul class=\"dropdown-menu pull-xs-right\">\n                        <li><a class=\"dropdown-item\" href=\"#\"><i class=\"fa fa-reply reply-btn\"></i> Reply</a></li>\n                        <li><a class=\"dropdown-item\" href=\"#\"><i class=\"fa fa-arrow-right reply-btn\"></i> Forward</a></li>\n                        <li><a class=\"dropdown-item\" href=\"#\"><i class=\"fa fa-print\"></i> Print</a></li>\n                        <li class=\"dropdown-divider\"></li>\n                        <li><a class=\"dropdown-item\" href=\"#\"><i class=\"fa fa-ban\"></i> Spam</a></li>\n                        <li><a class=\"dropdown-item\" href=\"javascript:void(0)\" (click)=\"trash(mail.id)\"><i class=\"fa fa-trash\"></i> Delete</a></li>\n                    </ul>\n                </div>\n            </div>\n        </div>\n        <div [innerHTML]=\"mail.body\"></div>\n\n        <div class=\"mail-attachments\" *ngIf=\"mail.attachments.length > 0\">\n            <hr/>\n            <div class=\"row\">\n                <div class=\"col-sm-6\">                    \n                    <p>\n                        <strong>{{mail.attachments.length}} attachments</strong> &nbsp;-&nbsp; \n                        <a href=\"#\">Download all attachments</a>&nbsp;&nbsp;&nbsp;\n                        <a href=\"#\">View all Images</a>\n                    </p>\n                    <section *ngFor=\"let attachment of mail.attachments; let i = index\" class=\"attachment\">\n                        <img src=\"{{attachment}}\" alt=\"\">\n                        <h5 class=\"title\">image-{{i + 1}}.jpg</h5>\n                        <p>\n                            457K  &nbsp;&nbsp;\n                            <a href=\"#\">View</a> &nbsp;&nbsp;\n                            <a href=\"#\">Download</a>\n                        </p>\n                    </section>\n                </div>\n            </div>\n        </div>\n\n    </div>\n</div>\n"

/***/ }),

/***/ "../../../../../src/app/pages/mail/mail-detail/mail-detail.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MailDetailComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__mail_service__ = __webpack_require__("../../../../../src/app/pages/mail/mail.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var MailDetailComponent = (function () {
    function MailDetailComponent(service, route, router) {
        this.service = service;
        this.route = route;
        this.router = router;
        this.replyMessage = new __WEBPACK_IMPORTED_MODULE_0__angular_core__["EventEmitter"]();
    }
    MailDetailComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.params
            .switchMap(function (params) { return _this.service.getMail(+params['id']); })
            .subscribe(function (mail) { return _this.mail = mail; });
    };
    MailDetailComponent.prototype.goToReply = function (mail) {
        this.replyMessage.emit(mail);
    };
    MailDetailComponent.prototype.trash = function (id) {
        this.service.getMail(id).then(function (mail) {
            mail.trash = true;
            mail.sent = false;
            mail.draft = false;
            mail.starred = false;
        });
        this.router.navigate(['pages/mail/mail-list/inbox']);
    };
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Output"])(),
        __metadata("design:type", Object)
    ], MailDetailComponent.prototype, "replyMessage", void 0);
    MailDetailComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'az-mail-detail',
            encapsulation: __WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewEncapsulation"].None,
            template: __webpack_require__("../../../../../src/app/pages/mail/mail-detail/mail-detail.component.html")
        }),
        __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_2__mail_service__["a" /* MailService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__mail_service__["a" /* MailService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* ActivatedRoute */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["d" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["d" /* Router */]) === "function" && _c || Object])
    ], MailDetailComponent);
    return MailDetailComponent;
    var _a, _b, _c;
}());

//# sourceMappingURL=mail-detail.component.js.map

/***/ }),

/***/ "../../../../../src/app/pages/mail/mail-list/mail-list.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"table-header\">\n     <div class=\"abc-checkbox inbox-checkbox\">    \n        <input id=\"toggle-all\" type=\"checkbox\" [(ngModel)]=\"isAllSelected\" (click)=\"toggleAll()\">      \n        <label for=\"toggle-all\"></label>\n    </div>\n    <div class=\"w-200 pull-right\">\n         <input class=\"form-control form-control-sm\" id=\"table-search-input\" [(ngModel)]=\"searchText\" type=\"text\" placeholder=\"Search mail...\">\n    </div>\n</div>\n<div class=\"table-responsive\">\n    <table class=\"table inbox-table\">\n        <tbody>\n            <tr *ngFor=\"let mail of mails | async | MailSearch : searchText\" [ngClass]=\"{'unread': mail.unread, 'selected': mail.selected}\">\n                <td class=\"mail-checkbox\">\n                    <div class=\"abc-checkbox inbox-checkbox\">\n                        <input id=\"checkbox{{mail.id}}\" type=\"checkbox\" [(ngModel)]=\"mail.selected\" (ngModelChange)=\"toggleOne()\">                      \n                        <label attr.for=\"checkbox{{mail.id}}\"></label>\n                    </div>\n                </td>\n                <td class=\"mail-star\" *ngIf=\"router.url != '/pages/mail/mail-list/trash'\">\n                    <i class=\"fa\" [ngClass]=\"{'fa-star': mail.starred, 'fa-star-o': !mail.starred}\" (click)=\"changeStarStatus(mail)\"></i>\n                </td>           \n                <td (click)=\"goToDetail(mail)\" class=\"sender\">{{ mail.sender }}</td>\n                <td (click)=\"goToDetail(mail)\" class=\"subject\" [innerHTML]=\"mail.subject\"></td>\n                <td (click)=\"goToDetail(mail)\" class=\"attachment\"><i *ngIf=\"mail.attachment\" class=\"fa fa-paperclip\"></i></td>\n                <td (click)=\"goToDetail(mail)\" class=\"date\">{{mail.date}}</td>\n            </tr>\n        </tbody>\n    </table>\n</div>"

/***/ }),

/***/ "../../../../../src/app/pages/mail/mail-list/mail-list.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MailListComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_switchMap__ = __webpack_require__("../../../../rxjs/add/operator/switchMap.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_switchMap___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_switchMap__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__mail_service__ = __webpack_require__("../../../../../src/app/pages/mail/mail.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__app_state__ = __webpack_require__("../../../../../src/app/app.state.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var MailListComponent = (function () {
    function MailListComponent(service, route, router, state) {
        var _this = this;
        this.service = service;
        this.route = route;
        this.router = router;
        this.state = state;
        this.router.events.subscribe(function (event) {
            if (event instanceof __WEBPACK_IMPORTED_MODULE_2__angular_router__["b" /* NavigationEnd */]) {
                _this.unSelectAll();
                _this.searchText = '';
            }
        });
        this.state.subscribe('markAsRead', function (val) {
            _this.markAllAsRead();
        });
        this.state.subscribe('markAsUnRead', function (val) {
            _this.markAllAsUnRead();
        });
        this.state.subscribe('deleteChecked', function (val) {
            _this.deleteAllCheckedMail();
        });
    }
    MailListComponent.prototype.ngOnInit = function () {
        this.getMails();
    };
    MailListComponent.prototype.getMails = function () {
        var _this = this;
        this.mails = this.route.params.switchMap(function (params) {
            _this.type = params['type'];
            switch (_this.type) {
                case 'inbox':
                    return _this.service.getInboxMails();
                case 'starred':
                    return _this.service.getStarredMails();
                case 'sent':
                    return _this.service.getSentMails();
                case 'drafts':
                    return _this.service.getDraftMails();
                case 'trash':
                    return _this.service.getTrashMails();
                default:
                    return _this.service.getInboxMails();
            }
        });
    };
    MailListComponent.prototype.toggleAll = function () {
        var toggleStatus = !this.isAllSelected;
        this.mails.subscribe(function (result) {
            result.forEach(function (mail) {
                mail.selected = toggleStatus;
            });
        });
    };
    MailListComponent.prototype.toggleOne = function () {
        var _this = this;
        this.mails.subscribe(function (result) {
            _this.isAllSelected = result.every(function (mail) {
                return mail.selected == true;
            });
        });
    };
    MailListComponent.prototype.unSelectAll = function () {
        this.isAllSelected = false;
        if (this.mails) {
            this.mails.subscribe(function (result) {
                result.forEach(function (mail) {
                    mail.selected = false;
                });
            });
        }
    };
    MailListComponent.prototype.markAllAsRead = function () {
        this.mails.subscribe(function (result) {
            result.forEach(function (mail) {
                if (mail.selected)
                    mail.unread = false;
            });
        });
    };
    MailListComponent.prototype.markAllAsUnRead = function () {
        this.mails.subscribe(function (result) {
            result.forEach(function (mail) {
                if (mail.selected)
                    mail.unread = true;
            });
        });
    };
    MailListComponent.prototype.deleteAllCheckedMail = function () {
        this.mails.subscribe(function (result) {
            result.forEach(function (mail) {
                if (mail.selected) {
                    mail.trash = true;
                    mail.sent = false;
                    mail.draft = false;
                    mail.starred = false;
                }
            });
        });
        this.getMails();
        this.isAllSelected = false;
    };
    MailListComponent.prototype.goToDetail = function (mail) {
        mail.unread = false;
        this.router.navigate(['pages/mail/mail-list/' + this.type, mail.id]);
    };
    MailListComponent.prototype.changeStarStatus = function (mail) {
        mail.starred = !mail.starred;
    };
    MailListComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'az-mail-list',
            encapsulation: __WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewEncapsulation"].None,
            template: __webpack_require__("../../../../../src/app/pages/mail/mail-list/mail-list.component.html")
        }),
        __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_3__mail_service__["a" /* MailService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__mail_service__["a" /* MailService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["a" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["a" /* ActivatedRoute */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["d" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["d" /* Router */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_4__app_state__["a" /* AppState */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__app_state__["a" /* AppState */]) === "function" && _d || Object])
    ], MailListComponent);
    return MailListComponent;
    var _a, _b, _c, _d;
}());

//# sourceMappingURL=mail-list.component.js.map

/***/ }),

/***/ "../../../../../src/app/pages/mail/mail.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"inbox-container\">\n    <div class=\"row\">\n        <div class=\"col-sm-3 col-md-2\">\n            <div class=\"input-group input-group-sm\">\n                <div class=\"input-group-btn\">\n                    <button aria-expanded=\"false\" aria-haspopup=\"true\" class=\"btn btn-main dropdown-toggle\" data-toggle=\"dropdown\" type=\"button\">\n                            Mail\n                    </button>\n                    <div class=\"dropdown-menu\">\n                        <a class=\"dropdown-item\" href=\"javascript:void(0);\">Mail</a>\n                        <a class=\"dropdown-item\" href=\"javascript:void(0);\">Contacts</a>\n                        <a class=\"dropdown-item\" href=\"javascript:void(0);\">Tasks</a>\n                    </div>\n                </div>\n            </div>\n        </div>\n        <div class=\"col-sm-9 col-md-10\">\n            <div class=\"b-btn\" [style.display]=\"(id || !type) ? 'inline-block' : 'none'\" data-toggle=\"tooltip\" data-placement=\"top\" data-animation=\"false\" title=\"Back to {{type}}\">               \n                <button (click)=\"getBack()\" type=\"button\" class=\"btn btn-secondary btn-sm\">\n                    <i class=\"fa fa-arrow-left\"></i>\n                </button>\n            </div> \n            <div class=\"b-btn\" *ngIf=\"id\" data-toggle=\"tooltip\" data-placement=\"top\" data-animation=\"false\" title=\"Archive\">               \n                <button type=\"button\" class=\"btn btn-secondary btn-sm\">\n                    <i class=\"fa fa-archive\"></i>\n                </button>\n            </div> \n            <div class=\"b-btn\" *ngIf=\"id\" data-toggle=\"tooltip\" data-placement=\"top\" data-animation=\"false\" title=\"Report Spam\">               \n                <button type=\"button\" class=\"btn btn-secondary btn-sm\">\n                    <i class=\"fa fa-exclamation-circle\"></i>\n                </button>\n            </div> \n            <div class=\"b-btn\" *ngIf=\"id\" data-toggle=\"tooltip\" data-placement=\"top\" data-animation=\"false\" title=\"Delete\">               \n                <button (click)=\"trash()\" type=\"button\" class=\"btn btn-secondary btn-sm\">\n                    <i class=\"fa fa-trash\"></i>\n                </button>\n            </div> \n         \n            <div class=\"b-btn\" [style.display]=\"(router.url == '/pages/mail/mail-compose' || id) ? 'none' : 'inline-block'\" data-toggle=\"tooltip\" data-placement=\"top\" data-animation=\"false\" title=\"Refresh\">\n                <button type=\"button\" class=\"btn btn-secondary btn-sm\"><span class=\"fa fa-refresh\"></span></button>\n            </div> \n            <div class=\"b-btn\" [style.display]=\"(router.url == '/pages/mail/mail-compose') ? 'none' : 'inline-block'\">\n                <div class=\"input-group input-group-sm d-inline\">\n                    <div class=\"input-group-btn d-inline\">\n                        <button aria-expanded=\"false\" aria-haspopup=\"true\" class=\"btn btn-secondary dropdown-toggle\" data-toggle=\"dropdown\" type=\"button\">\n                                More\n                        </button>\n                        <div class=\"dropdown-menu m-t-15\">\n                            <a class=\"dropdown-item\" href=\"javascript:void(0);\" (click)=\"setAsRead()\">Mark as read</a>\n                            <a class=\"dropdown-item\" href=\"javascript:void(0);\" (click)=\"setAsUnRead()\">Mark as unread</a>\n                            <a class=\"dropdown-item\" href=\"javascript:void(0);\" (click)=\"deleteCheckedMail()\">Delete</a>\n                        </div>\n                    </div>\n                </div>\n            </div> \n            <div class=\"pull-right\">\n                <span class=\"inbox-paging\"><b>1</b>–<b>50</b> of <b>277</b></span>\n                <div class=\"input-group input-group-sm d-inline\">\n                    <div class=\"input-group-btn d-inline\">\n                        <button type=\"button\" class=\"btn btn-secondary\">\n                            <span class=\"fa fa-chevron-left\"></span>\n                        </button>\n                        <button type=\"button\" class=\"btn btn-secondary\">\n                            <span class=\"fa fa-chevron-right\"></span>\n                        </button>\n                    </div>\n                </div>\n            </div>\n        </div>\n    </div>\n    <hr />\n\n    <div class=\"row\">\n        <div class=\"col-sm-3 col-md-2\">\n            <a [routerLink]=\"['mail-compose']\" class=\"btn btn-danger btn-sm btn-block\" role=\"button\">COMPOSE</a>\n            <hr />\n            <ul class=\"nav flex-column\">\n                <li class=\"nav-item\"><a [routerLink]=\"['mail-list/inbox']\"><span class=\"badge badge-pill badge-main pull-right\">7</span> Inbox </a></li>\n                <li class=\"nav-item\"><a [routerLink]=\"['mail-list/starred']\">Starred</a></li>                \n                <li class=\"nav-item\"><a [routerLink]=\"['mail-list/sent']\">Sent Mail</a></li>\n                <li class=\"nav-item\"><a [routerLink]=\"['mail-list/drafts']\"><span class=\"badge badge-pill badge-main pull-right\">2</span>Drafts</a></li>           \n                <li class=\"nav-item\"><a [routerLink]=\"['mail-list/trash']\">Trash</a></li>\n            </ul>\n            <hr/>\n            <ul class=\"nav flex-column\">\n                <li class=\"nav-item\">\n                    <a data-toggle=\"collapse\" href=\"#circles\" aria-expanded=\"false\" aria-controls=\"circles\"><i class=\"fa fa-caret-down pull-right\"></i> Circles </a>                  \n                    <div class=\"collapse\" id=\"circles\">\n                       <ul class=\"nav flex-column\">\n                          <li class=\"nav-item\"><a href=\"#\"><i class=\"fa fa-circle-o text-muted pull-right\"></i> Friends </a></li>\n                          <li class=\"nav-item\"><a href=\"#\"><i class=\"fa fa-circle-o text-muted pull-right\"></i> Family </a></li>\n                          <li class=\"nav-item\"><a href=\"#\"><i class=\"fa fa-circle-o text-muted pull-right\"></i> Work </a></li>\n                       </ul>\n                    </div>                   \n                </li>\n                <li class=\"nav-item\"><a href=\"#\">Notes</a></li>\n                <li class=\"nav-item\"><a href=\"#\">Personal</a></li>\n                <li class=\"nav-item\"><a href=\"#\">Travel</a></li>                   \n                <li class=\"nav-item\">\n                    <a data-toggle=\"collapse\" href=\"#more\" aria-expanded=\"false\" aria-controls=\"more\"><i class=\"fa fa-caret-down pull-right\"></i> More </a>                  \n                    <div class=\"collapse\" id=\"more\">\n                        <ul class=\"nav flex-column\">\n                            <li class=\"nav-item\"><a href=\"#\"><i class=\"fa fa-circle text-danger pull-right\"></i> Chats </a></li>\n                            <li class=\"nav-item\"><a href=\"#\"><i class=\"fa fa-circle text-white pull-right\"></i> Spam </a></li>\n                            <li class=\"nav-item\"><a href=\"#\"><i class=\"fa fa-circle pull-right\"></i> Trash </a></li>\n                        </ul>\n                    </div>                   \n                </li>\n            </ul>          \n        </div>\n        <div class=\"col-sm-9 col-md-10\">\n            <div class=\"inbox-body\">\n                <router-outlet></router-outlet>\n            </div>            \n        </div>\n    </div>\n\n    <hr />\n\n\n</div>\n\n"

/***/ }),

/***/ "../../../../../src/app/pages/mail/mail.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "/**** Colors (recommended use HEX colors) ****/\n/**** App roots ****/\n/**** General ****/\n/**** Navbar ****/\n/**** Sidebar ****/\n/**** Progress ****/\n/**** Fonts ****/\n/**** Social icons ****/\nbody::after {\n  content: '{\"main-color\":\"#242D3A\",\"default-color\":\"#fff\",\"dark-color\":\"#000\",\"primary-color\":\"#024a88\",\"success-color\":\"#2d922d\",\"warning-color\":\"#f79a17\",\"danger-color\":\"#bf1725\",\"info-color\":\"#248dad\",\"sidebar-bg-color\":\"#344154\",\"gray\":\"#555\",\"gray-light\":\"#ccc\"}';\n  display: none; }\n\n.inbox-container .b-btn {\n  display: inline-block; }\n\n.inbox-container .check-all {\n  position: relative;\n  top: 2px;\n  margin: 0 4px; }\n\n.inbox-container .inbox-paging {\n  color: #555; }\n\n.inbox-container .inbox-checkbox {\n  display: -webkit-inline-box;\n  display: -ms-inline-flexbox;\n  display: inline-flex;\n  padding-left: 0; }\n  .inbox-container .inbox-checkbox label {\n    margin-bottom: 0;\n    padding-left: 0; }\n    .inbox-container .inbox-checkbox label::before {\n      width: 15px;\n      height: 15px;\n      margin-left: -14px;\n      border-radius: 0;\n      top: 1px; }\n    .inbox-container .inbox-checkbox label::after {\n      margin-left: -15px;\n      padding-top: 1px; }\n\n.inbox-container .nav-item a {\n  display: block;\n  padding: 2px 3px;\n  color: #555; }\n  .inbox-container .nav-item a i {\n    margin-top: 3px; }\n  .inbox-container .nav-item a:hover {\n    background: #242D3A;\n    color: #fff; }\n\n.inbox-container .nav-item ul {\n  padding-left: 4px; }\n\n.inbox-container .inbox-body {\n  background: #fff; }\n\n.inbox-container .inbox-table {\n  margin-bottom: 0; }\n  .inbox-container .inbox-table > tbody > tr {\n    cursor: pointer; }\n    .inbox-container .inbox-table > tbody > tr.unread td {\n      font-weight: bold; }\n    .inbox-container .inbox-table > tbody > tr.selected {\n      background: #f7f7f7; }\n    .inbox-container .inbox-table > tbody > tr:hover {\n      background: #f7f7f7; }\n    .inbox-container .inbox-table > tbody > tr > td {\n      padding: 0.4rem; }\n      .inbox-container .inbox-table > tbody > tr > td.mail-checkbox {\n        width: 34px;\n        padding-left: 10px; }\n        .inbox-container .inbox-table > tbody > tr > td.mail-checkbox .custom-control {\n          margin-bottom: 0;\n          padding-left: 0; }\n          .inbox-container .inbox-table > tbody > tr > td.mail-checkbox .custom-control .custom-control-indicator {\n            top: 0.17rem; }\n        .inbox-container .inbox-table > tbody > tr > td.mail-checkbox .inbox-checkbox label::after {\n          margin-left: -15px;\n          padding-top: 1px; }\n      .inbox-container .inbox-table > tbody > tr > td.mail-star {\n        width: 28px;\n        font-size: 15px;\n        color: rgba(0, 0, 0, 0.3); }\n        .inbox-container .inbox-table > tbody > tr > td.mail-star i.fa-star {\n          color: #f79a17; }\n      .inbox-container .inbox-table > tbody > tr > td.sender {\n        white-space: nowrap;\n        text-overflow: ellipsis;\n        max-width: 150px;\n        overflow: hidden; }\n      .inbox-container .inbox-table > tbody > tr > td.subject {\n        white-space: nowrap;\n        text-overflow: ellipsis;\n        max-width: 500px;\n        overflow: hidden; }\n      .inbox-container .inbox-table > tbody > tr > td.attachment {\n        width: 24px;\n        text-align: center; }\n      .inbox-container .inbox-table > tbody > tr > td.date {\n        width: 80px;\n        text-align: right;\n        padding-right: 6px;\n        white-space: nowrap; }\n\n.inbox-container .table-header {\n  padding: 0.4rem 0.6rem;\n  overflow: hidden; }\n\n.inbox-container .detail-content .avatar {\n  width: 38px;\n  height: 38px;\n  margin-right: 10px;\n  background: #e9ebee;\n  border: 1px solid #ccc;\n  color: #ccc;\n  text-align: center;\n  padding: 3px;\n  font-size: 20px; }\n\n.inbox-container .detail-content .mail-date {\n  line-height: 28px;\n  margin-right: 10px; }\n\n.compose {\n  overflow: hidden; }\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/pages/mail/mail.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MailComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__mail_service__ = __webpack_require__("../../../../../src/app/pages/mail/mail.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__app_state__ = __webpack_require__("../../../../../src/app/app.state.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var MailComponent = (function () {
    function MailComponent(service, route, router, state) {
        var _this = this;
        this.service = service;
        this.route = route;
        this.router = router;
        this.state = state;
        this.markAsRead = false;
        this.markAsUnRead = false;
        this.deleteChecked = false;
        this.router.events.subscribe(function (event) {
            if (event instanceof __WEBPACK_IMPORTED_MODULE_1__angular_router__["b" /* NavigationEnd */]) {
                _this.id = _this.route.snapshot.firstChild.params['id'];
                _this.type = _this.route.snapshot.firstChild.params['type'];
                setTimeout(function () {
                    jQuery('[data-toggle="tooltip"]').tooltip({ trigger: "hover" });
                });
            }
        });
    }
    MailComponent.prototype.getBack = function () {
        if (this.type)
            this.router.navigate(['pages/mail/mail-list/' + this.type]);
        else
            this.router.navigate(['pages/mail/mail-list/inbox']);
    };
    MailComponent.prototype.trash = function () {
        jQuery('[data-toggle="tooltip"]').tooltip('hide');
        this.service.getMail(this.id).then(function (mail) {
            mail.trash = true;
            mail.sent = false;
            mail.draft = false;
            mail.starred = false;
        });
        this.router.navigate(['pages/mail/mail-list/inbox']);
    };
    MailComponent.prototype.setAsRead = function () {
        this.markAsRead = !this.markAsRead;
        this.state.notifyDataChanged('markAsRead', this.markAsRead);
    };
    MailComponent.prototype.setAsUnRead = function () {
        this.markAsUnRead = !this.markAsUnRead;
        this.state.notifyDataChanged('markAsUnRead', this.markAsUnRead);
    };
    MailComponent.prototype.deleteCheckedMail = function () {
        this.deleteChecked = !this.deleteChecked;
        this.state.notifyDataChanged('deleteChecked', this.deleteChecked);
    };
    MailComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'az-mail',
            encapsulation: __WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewEncapsulation"].None,
            template: __webpack_require__("../../../../../src/app/pages/mail/mail.component.html"),
            styles: [__webpack_require__("../../../../../src/app/pages/mail/mail.component.scss")],
            providers: [__WEBPACK_IMPORTED_MODULE_2__mail_service__["a" /* MailService */]]
        }),
        __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_2__mail_service__["a" /* MailService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__mail_service__["a" /* MailService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* ActivatedRoute */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["d" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["d" /* Router */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_3__app_state__["a" /* AppState */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__app_state__["a" /* AppState */]) === "function" && _d || Object])
    ], MailComponent);
    return MailComponent;
    var _a, _b, _c, _d;
}());

//# sourceMappingURL=mail.component.js.map

/***/ }),

/***/ "../../../../../src/app/pages/mail/mail.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "routes", function() { return routes; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MailModule", function() { return MailModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common__ = __webpack_require__("../../../common/@angular/common.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_forms__ = __webpack_require__("../../../forms/@angular/forms.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__theme_pipes_pipes_module__ = __webpack_require__("../../../../../src/app/theme/pipes/pipes.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__mail_component__ = __webpack_require__("../../../../../src/app/pages/mail/mail.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__mail_compose_mail_compose_component__ = __webpack_require__("../../../../../src/app/pages/mail/mail-compose/mail-compose.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__mail_list_mail_list_component__ = __webpack_require__("../../../../../src/app/pages/mail/mail-list/mail-list.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__mail_detail_mail_detail_component__ = __webpack_require__("../../../../../src/app/pages/mail/mail-detail/mail-detail.component.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};









var routes = [
    { path: '',
        component: __WEBPACK_IMPORTED_MODULE_5__mail_component__["a" /* MailComponent */],
        breadcrumb: {
            title: 'Mail'
        },
        children: [
            { path: '', redirectTo: 'mail-list/inbox', pathMatch: 'full' },
            { path: 'mail-list/:type', component: __WEBPACK_IMPORTED_MODULE_7__mail_list_mail_list_component__["a" /* MailListComponent */], data: { breadcrumb: 'Inbox' } },
            { path: 'mail-compose', component: __WEBPACK_IMPORTED_MODULE_6__mail_compose_mail_compose_component__["a" /* MailComposeComponent */], data: { breadcrumb: 'Compose' } },
            { path: 'mail-list/:type/:id', component: __WEBPACK_IMPORTED_MODULE_8__mail_detail_mail_detail_component__["a" /* MailDetailComponent */], data: { breadcrumb: 'Detail' } }
        ]
    }
];
var MailModule = (function () {
    function MailModule() {
    }
    MailModule = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["NgModule"])({
            imports: [
                __WEBPACK_IMPORTED_MODULE_1__angular_common__["CommonModule"],
                __WEBPACK_IMPORTED_MODULE_3__angular_forms__["FormsModule"],
                __WEBPACK_IMPORTED_MODULE_4__theme_pipes_pipes_module__["a" /* PipesModule */],
                __WEBPACK_IMPORTED_MODULE_2__angular_router__["e" /* RouterModule */].forChild(routes)
            ],
            declarations: [
                __WEBPACK_IMPORTED_MODULE_5__mail_component__["a" /* MailComponent */],
                __WEBPACK_IMPORTED_MODULE_6__mail_compose_mail_compose_component__["a" /* MailComposeComponent */],
                __WEBPACK_IMPORTED_MODULE_7__mail_list_mail_list_component__["a" /* MailListComponent */],
                __WEBPACK_IMPORTED_MODULE_8__mail_detail_mail_detail_component__["a" /* MailDetailComponent */]
            ]
        })
    ], MailModule);
    return MailModule;
}());

//# sourceMappingURL=mail.module.js.map

/***/ }),

/***/ "../../../../../src/app/pages/mail/mail.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* unused harmony export Mail */
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return MailService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var Mail = (function () {
    function Mail(id, sender, senderMail, subject, date, body, attachment, attachments, unread, sent, starred, draft, trash, selected) {
        this.id = id;
        this.sender = sender;
        this.senderMail = senderMail;
        this.subject = subject;
        this.date = date;
        this.body = body;
        this.attachment = attachment;
        this.attachments = attachments;
        this.unread = unread;
        this.sent = sent;
        this.starred = starred;
        this.draft = draft;
        this.trash = trash;
        this.selected = selected;
    }
    return Mail;
}());

var Mails = [
    new Mail(1, 'Envato Market', 'do-not-reply@market.envato.com', 'Your updated item has been approved', '4:08 PM', '<p>Congratulations! Your update to Azimuth - Angular 2 Admin Template on ThemeForest has been approved. ' +
        'You can view your item here: </p>' +
        '<p><a href="http://themeforest.net/item/azimuth-angular-2-admin-template/19182105" target="blank">http://themeforest.net/item/azimuth-angular-2-admin-template/19182105</a></p>' +
        '<p>Thanks for your submission!</p>' +
        '<p>Regards,<br> Envato Market Team</p>', false, [], true, false, false, false, false, false),
    new Mail(2, 'Josiah Fromdahl', 'Reekie8647@gmail.com', 'Useful tool for those who are involved in SEO', '9:47 AM', '<p>Hi, I want to introduce the Website Reviewer is an incredibly useful tool for those who are involved in SEO and web designing. ' +
        'This particular tool will provide you with quick website review and SEO audit of the websites you’ve created so ' +
        'you will be able to determine if and where any changes should be made so you can make it as effective ' +
        'as possible when it comes to getting visitors and keeping them interested. ' +
        'Unlike many similar tools, website reviewer is completely free.</p>', true, ['assets/img/app/snow.jpg', 'assets/img/app/sample.jpg'], true, false, true, false, false, false),
    new Mail(3, 'Google Cloud Platform', 'CloudPlatform-noreply@google.com', 'Lessons from the field: surviving success with Customer Reliability Engineering', 'Jan 5', '<h4>TRENDING</h4>' +
        '<p>For those who missed the early adoption of Infrastructure as a Service circa 2007,' +
        'this in-depth history stresses why businesses need to begin building around "serverless" architectures.</p>', false, [], false, false, false, false, false, false),
    new Mail(4, 'Microsoft Visual Studio', 'MVS@e-mail.microsoft.com', 'Welcome to Visual Studio Team Services', '24.12.2016', 'Whether your teams develop in Java, .NET, or in multiple languages, Visual Studio Team Services offers an open, cloud-hosted development hub. Use your favorite IDE, develop in any language, and empower your teams to iterate rapidly', false, [], true, false, false, false, false, false),
    new Mail(5, 'Draft', '', 'no subject', '4:46 PM', '', false, [], false, false, false, true, false, false),
    new Mail(6, 'Draft', '', 'Please confirm your email', 'Jan 7', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut elementum interdum ex, sed aliquet nisl maximus imperdiet. Phasellus pharetra nunc eu dui hendrerit, quis ullamcorper tortor malesuada. Nullam ante mi, auctor eu nunc vitae, gravida molestie arcu.', false, [], false, false, false, true, false, false),
    new Mail(7, 'MailChimp', 'hello@mailchimp.com', 'Our 2016 annual report', '22.12.2016', '<p>For our 2016 annual report, we let our design team run wild with our most fun facts and proudest moments from the year. From GIFs in email campaigns to physical tons of infrastructure added to dollars raised for charity, it’s our year in numbers.</p>' +
        '<p>We’re proud of our new features, our customer support tickets solved, and our philanthropy in Atlanta. But we’re even more proud of you and the billions of emails you’ve sent to grow your businesses your way in 2016. Keep up the good work!</p>' +
        '<p>Cheers to the new year,</p>' +
        '<p>MailChimp</p>', false, [], false, false, false, false, false, false),
    new Mail(8, 'Bluehost', 'no-reply@e.bluehost.com', 'Your domain privacy may be at risk', '20.12.2016', '<h4>New ICANN Rules Require Accurate Contact Information</h4>' +
        '<p>Your domain information may be at risk. However, you can protect your information with domain privacy.</p>' +
        '<p>Protect yourself with Domain Privacy.</p>' +
        '<p><small>Only $11.88 per year.**</small></p>' +
        '<p><a href="http://bluehost.com/" target="blank" class="btn btn-success">Get started</a></p>', false, [], false, false, true, false, false, false),
    new Mail(9, 'Vimeo', 'vimeo@email.vimeo.com', 'The year\'s best videos and milestones', '20.12.2016', '<h4>Now presenting: 2016\'s best videos</h4>' +
        '<p>Every day, the world\'s best creators upload their videos to Vimeo. And now, the most inventive, most striking, most all-around mind-blowing videos of 2016 are in. Enjoy, wondrous humans.</p>' +
        '<p><a href="https://vimeo.com/blog/post/The-Top-Videos-of-2016?utm_source=email&utm_medium=vimeo-decembernewsletter-201612&utm_campaign=30389" target="blank" class="btn btn-info">See the list</a></p>', false, [], false, false, false, false, false, false),
    new Mail(10, 'Andy Dufresne', 'andy.dufresne@gmail.com', 'The Shawshank Redemption', '18.11.2016', '<p>From Wikipedia, the free encyclopedia</p>' +
        '<p>The Shawshank Redemption is a 1994 American drama film written and directed by Frank Darabont. and starring Tim Robbins and Morgan Freeman. Adapted from the Stephen King novella Rita Hayworth and Shawshank Redemption, the film tells the story of Andy Dufresne, ' +
        'a banker who is sentenced to life in Shawshank State Penitentiary for the murder of his wife and her lover, despite his claims of innocence. During his time at the prison, he befriends a fellow inmate, Ellis Boyd "Red" Redding, ' +
        'and finds himself protected by the guards after the warden begins using him in his money-laundering operation.</p>', false, [], false, true, false, false, false, false),
    new Mail(11, 'The Envato Team', 'donotreply@envato.com', 'Author Driven Pricing on ThemeForest, CodeCanyon and 3DOcean', '05.09.2016', '<h5>Hey Envato Community Members!</h5>' +
        '<p>As many of you know, over the past 12 months we have been rolling out Author Driven Pricing (ADP) across a range of different categories on Envato Market. So far this has included GraphicRiver and a handful of categories within ThemeForest.</p>' +
        '<p>In a few weeks, we\'ll be moving on to the next stage of our Author Driven Pricing project. This will involve introducing ADP to three new areas of Envato Market. These areas are:</p>' +
        '<ul><li>All remaining ThemeForest categories (including WordPress). </li><li>All categories on CodeCanyon.</li><li>All categories on 3DOcean</li></ul>' +
        '<p>We strongly recommend reading the full announcement on our forums where you will find:</p>' +
        '<ul><li>More details about the change.</li><li>How ADP has impacted pricing on previous categories.</li><li>Technical details regarding how to set prices.</li> <li>Why we’ve made the change. </li></ul>' +
        '<p>You will also be able to ask any questions you might have.</p>' +
        '<p>We will send you another email to let you know when this feature goes live.</p>' +
        '<p>All the best,</p>' +
        '<p>The Envato Team</p>', false, [], false, false, false, false, false, false),
    new Mail(12, 'Jonathan Cantu', 'JCantu@billingsleyco.com', 'Ability to Customize', '02.09.2016', '<p>Hi,</p><p>Upon purchases, will I have the ability to modify color palette, font, etc. beyond your own design?</p><p>Thanks</p>', false, [], false, false, false, false, false, false),
    new Mail(13, 'Jennifer Adam', 'jenniferadam2009@gmail.com', 'Guest Post Request - 2016 !!!', '09.05.2016', '<p><b>Hello,</b></p>' +
        '<p>I\'m Jennifer, constant follower of your blog posts and I\'ve liked most of the blog posts written here. I could realize you\'ve already published some great guest posts from different authors with distinct styles ' +
        'and I\'d be privileged if I were provided with an opportunity to delight your blog followers with some informative blog posts.</p>' +
        '<p>I\'m a content expert and I\'ve contributed some sensible and informative articles to surplus niche-specific blogs. I realize the value of content and its part in helping out numerous people out there in the space. </p>', false, [], false, false, false, false, false, false)
];
var mailsPromise = Promise.resolve(Mails);
var MailService = (function () {
    function MailService() {
    }
    MailService.prototype.getInboxMails = function () {
        return mailsPromise
            .then(function (mails) { return mails.filter(function (mail) { return mail.sent == false && mail.draft == false && mail.trash == false; }); });
    };
    MailService.prototype.getStarredMails = function () {
        return mailsPromise
            .then(function (mails) { return mails.filter(function (mail) { return mail.starred == true; }); });
    };
    MailService.prototype.getSentMails = function () {
        return mailsPromise
            .then(function (mails) { return mails.filter(function (mail) { return mail.sent == true; }); });
    };
    MailService.prototype.getDraftMails = function () {
        return mailsPromise
            .then(function (mails) { return mails.filter(function (mail) { return mail.draft == true; }); });
    };
    MailService.prototype.getTrashMails = function () {
        return mailsPromise
            .then(function (mails) { return mails.filter(function (mail) { return mail.trash == true; }); });
    };
    MailService.prototype.getMail = function (id) {
        return mailsPromise
            .then(function (mails) { return mails.find(function (mail) { return mail.id === +id; }); });
    };
    MailService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])()
    ], MailService);
    return MailService;
}());

//# sourceMappingURL=mail.service.js.map

/***/ })

});
//# sourceMappingURL=mail.module.chunk.js.map