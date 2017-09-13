webpackJsonp(["login.module"],{

/***/ "../../../../../src/app/pages/login/login.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"login-container\">\n    <div class=\"col-xl-4 col-md-6 col-10 mx-auto\">\n        <div class=\"card\">\n            <div class=\"card-body text-center\">\n                <h2 class=\"card-title\">Sign in to Azimuth</h2>\n                <a routerLink=\"/register\" class=\"btn btn-link transition bottom-15\">Don't have an account? Sign up now!</a>\n\n                <form [formGroup]=\"form\" (ngSubmit)=\"onSubmit(form.value)\" class=\" text-left\">\n                    <div class=\"form-group\">\n                        <input [formControl]=\"email\" class=\"form-control checking-field\" placeholder=\"Email\" type=\"text\">                      \n                        <span class=\"help-block text-danger\" *ngIf=\"form.get('email').touched && form.get('email').hasError('required')\">Email is required</span>                              \n                        <span class=\"help-block text-danger\" *ngIf=\"form.get('email').touched && form.get('email').hasError('invalidEmail')\">Invalid email address</span>\n                    </div>\n                    <div class=\"form-group\">\n                        <input [formControl]=\"password\" class=\"form-control checking-field\" placeholder=\"Password\" type=\"password\">\n                        <span class=\"help-block text-danger\" *ngIf=\"form.get('password').touched && form.get('password').hasError('required')\">Password is required</span>                              \n                        <span class=\"help-block text-danger\" *ngIf=\"form.get('password').touched && form.get('password').hasError('minlength')\">Password isn't long enough, minimum of 6 characters</span>\n                    </div>\n                    <div class=\"form-group\">\n                        <label class=\"custom-control custom-checkbox\">\n                            <input class=\"custom-control-input checkbox-main\" type=\"checkbox\">\n                            <span class=\"custom-control-indicator\"></span>\n                            <span class=\"custom-control-description align-middle\">Keep me signed in.</span>\n                        </label>\n                        <a class=\"btn btn-link forgot transition pull-right\" href=\"javascript:void(0);\">Forgot password?</a>                     \n                    </div>\n                    <div class=\"form-group\">\n                        <button [disabled]=\"!form.valid\" class=\"btn btn-main btn-block\" type=\"submit\">Sign in</button>\n                    </div>\n                </form>\n              \n                <div class=\"auth-sep\"><span><span>or Sign in with one click</span></span></div>\n\n                <div class=\"form-group\">\n                    <ul class=\"login-help\">\n                        <li><i class=\"socicon socicon-facebook transition\"></i></li>\n                        <li><i class=\"socicon socicon-twitter transition\"></i></li>\n                        <li><i class=\"socicon socicon-google transition\"></i></li>              \n                    </ul>\n                </div>\n               \n            </div>\n        </div>\n    </div>\n</div>\n\n"

/***/ }),

/***/ "../../../../../src/app/pages/login/login.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "/**** Colors (recommended use HEX colors) ****/\n/**** App roots ****/\n/**** General ****/\n/**** Navbar ****/\n/**** Sidebar ****/\n/**** Progress ****/\n/**** Fonts ****/\n/**** Social icons ****/\nbody::after {\n  content: '{\"main-color\":\"#242D3A\",\"default-color\":\"#fff\",\"dark-color\":\"#000\",\"primary-color\":\"#024a88\",\"success-color\":\"#2d922d\",\"warning-color\":\"#f79a17\",\"danger-color\":\"#bf1725\",\"info-color\":\"#248dad\",\"sidebar-bg-color\":\"#344154\",\"gray\":\"#555\",\"gray-light\":\"#ccc\"}';\n  display: none; }\n\n.login-container {\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex;\n  -webkit-box-align: center;\n      -ms-flex-align: center;\n          align-items: center;\n  height: 100%;\n  width: 100%;\n  position: absolute; }\n  .login-container .card {\n    padding: 15px; }\n    .login-container .card .btn-link {\n      padding: 0; }\n      .login-container .card .btn-link.forgot {\n        font-size: 14px; }\n      .login-container .card .btn-link:hover {\n        color: #242D3A; }\n\n.auth-sep {\n  margin-top: 32px;\n  margin-bottom: 24px;\n  line-height: 20px;\n  font-size: 15px;\n  text-align: center;\n  display: block;\n  position: relative; }\n  .auth-sep > span {\n    display: table-cell;\n    width: 30%;\n    white-space: nowrap;\n    padding: 0 14px;\n    color: #555; }\n    .auth-sep > span > span {\n      margin-top: -11px;\n      display: block;\n      font-weight: 300; }\n  .auth-sep:before, .auth-sep:after {\n    border-top: solid 1px #ccc;\n    content: \"\";\n    height: 1px;\n    width: 35%;\n    display: table-cell; }\n\n.login-help {\n  margin: 0;\n  padding: 0; }\n  .login-help li {\n    list-style: none;\n    display: inline-block;\n    margin-left: 10px; }\n    .login-help li:first-child {\n      margin-left: 0; }\n    .login-help li i {\n      cursor: pointer;\n      transition: all 0.1s ease;\n      color: #fff;\n      padding: 9px 9px 6px 9px;\n      font-size: 12px;\n      background-color: #242D3A; }\n      .login-help li i.socicon-facebook {\n        background-color: #3b5998; }\n      .login-help li i.socicon-twitter {\n        background-color: #55acee; }\n      .login-help li i.socicon-google {\n        background-color: #dd4b39; }\n      .login-help li i:hover {\n        opacity: 0.9; }\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/pages/login/login.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LoginComponent; });
/* unused harmony export emailValidator */
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__("../../../forms/@angular/forms.es5.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var LoginComponent = (function () {
    function LoginComponent(router, fb) {
        this.router = router;
        this.form = fb.group({
            'email': ['', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["Validators"].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["Validators"].required, emailValidator])],
            'password': ['', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["Validators"].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["Validators"].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["Validators"].minLength(6)])]
        });
        this.email = this.form.controls['email'];
        this.password = this.form.controls['password'];
    }
    LoginComponent.prototype.onSubmit = function (values) {
        if (this.form.valid) {
            console.log(values);
            this.router.navigate(['pages/dashboard']);
        }
    };
    LoginComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'az-login',
            encapsulation: __WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewEncapsulation"].None,
            template: __webpack_require__("../../../../../src/app/pages/login/login.component.html"),
            styles: [__webpack_require__("../../../../../src/app/pages/login/login.component.scss")]
        }),
        __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["d" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["d" /* Router */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_forms__["FormBuilder"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_forms__["FormBuilder"]) === "function" && _b || Object])
    ], LoginComponent);
    return LoginComponent;
    var _a, _b;
}());

function emailValidator(control) {
    var emailRegexp = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
    if (control.value && !emailRegexp.test(control.value)) {
        return { invalidEmail: true };
    }
}
//# sourceMappingURL=login.component.js.map

/***/ }),

/***/ "../../../../../src/app/pages/login/login.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "routes", function() { return routes; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginModule", function() { return LoginModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common__ = __webpack_require__("../../../common/@angular/common.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_forms__ = __webpack_require__("../../../forms/@angular/forms.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__login_component__ = __webpack_require__("../../../../../src/app/pages/login/login.component.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};





var routes = [
    { path: '', component: __WEBPACK_IMPORTED_MODULE_4__login_component__["a" /* LoginComponent */], pathMatch: 'full' }
];
var LoginModule = (function () {
    function LoginModule() {
    }
    LoginModule = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["NgModule"])({
            imports: [
                __WEBPACK_IMPORTED_MODULE_1__angular_common__["CommonModule"],
                __WEBPACK_IMPORTED_MODULE_3__angular_forms__["FormsModule"],
                __WEBPACK_IMPORTED_MODULE_3__angular_forms__["ReactiveFormsModule"],
                __WEBPACK_IMPORTED_MODULE_2__angular_router__["e" /* RouterModule */].forChild(routes),
            ],
            declarations: [__WEBPACK_IMPORTED_MODULE_4__login_component__["a" /* LoginComponent */]]
        })
    ], LoginModule);
    return LoginModule;
}());

//# sourceMappingURL=login.module.js.map

/***/ })

});
//# sourceMappingURL=login.module.chunk.js.map