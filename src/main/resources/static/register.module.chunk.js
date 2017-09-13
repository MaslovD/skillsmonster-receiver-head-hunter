webpackJsonp(["register.module"],{

/***/ "../../../../../src/app/pages/register/register.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"register-container\">\n    <div class=\"col-xl-4 col-md-6 col-10 mx-auto\">\n        <div class=\"card\">\n            <div class=\"card-body text-center\">\n                <h2 class=\"card-title\">Sign up to Azimuth</h2>\n                <a routerLink=\"/login\" class=\"btn btn-link transition bottom-15\">Already have an account? Sign in!</a>\n                 \n                <form [formGroup]=\"form\" (ngSubmit)=\"onSubmit(form.value)\" class=\"text-left\">\n                    <div class=\"form-group\">\n                        <input [formControl]=\"name\" class=\"form-control checking-field\" placeholder=\"Full Name\" type=\"text\"> \n                        <span class=\"help-block\" *ngIf=\"form.get('name').touched\">\n                            <span class=\"text-danger\" *ngIf=\"form.get('name').hasError('required')\">Full Name is required</span>\n                            <span class=\"text-danger\" *ngIf=\"form.get('name').hasError('minlength')\">Minimum of 3 characters</span>\n                        </span>       \n                    </div>\n                    <div class=\"form-group\">\n                        <input [formControl]=\"email\" class=\"form-control checking-field\" placeholder=\"Email\" type=\"text\"> \n                        <span class=\"help-block\" *ngIf=\"form.get('email').touched\">\n                            <span class=\"text-danger\" *ngIf=\"form.get('email').hasError('required')\">Email is required</span>\n                            <span class=\"text-danger\" *ngIf=\"form.get('email').hasError('invalidEmail')\">Invalid email address</span>\n                        </span> \n                    </div>\n                    <div class=\"form-group\">\n                        <input [formControl]=\"password\" class=\"form-control checking-field\" placeholder=\"Password\" type=\"password\" minlength=\"6\">\n                         <span class=\"help-block\" *ngIf=\"form.get('password').touched\">\n                            <span class=\"text-danger\" *ngIf=\"form.get('password').hasError('required')\">Password is required</span>\n                            <span class=\"text-danger\" *ngIf=\"form.get('password').hasError('minlength')\">Password isn't long enough, minimum of 6 characters</span>\n                        </span>                     \n                    </div>\n                    <div class=\"form-group\">\n                        <input [formControl]=\"confirmPassword\" class=\"form-control checking-field\" placeholder=\"Confirm Password\" type=\"password\">\n                        <span class=\"help-block\" *ngIf=\"form.get('confirmPassword').touched\">\n                            <span class=\"text-danger\" *ngIf=\"form.get('confirmPassword').hasError('required')\">Confirm Password is required</span>\n                            <span class=\"text-danger\" *ngIf=\"form.get('confirmPassword').hasError('mismatchedPasswords')\">Passwords do not match</span>\n                        </span> \n                    </div>\n                    <div class=\"form-group\">\n                        <p class=\"terms\">By creating an account, you agree our <a class=\"btn btn-link transition terms\" href=\"javascript:void(0);\">Terms & Privacy Policy</a></p>                  \n                    </div>             \n                    <div class=\"form-group\">\n                        <button [disabled]=\"!form.valid\" class=\"btn btn-main btn-block\" type=\"submit\">Sign up</button>\n                    </div>\n                </form>  \n               \n            </div>\n        </div>\n    </div>\n</div>\n"

/***/ }),

/***/ "../../../../../src/app/pages/register/register.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "/**** Colors (recommended use HEX colors) ****/\n/**** App roots ****/\n/**** General ****/\n/**** Navbar ****/\n/**** Sidebar ****/\n/**** Progress ****/\n/**** Fonts ****/\n/**** Social icons ****/\nbody::after {\n  content: '{\"main-color\":\"#242D3A\",\"default-color\":\"#fff\",\"dark-color\":\"#000\",\"primary-color\":\"#024a88\",\"success-color\":\"#2d922d\",\"warning-color\":\"#f79a17\",\"danger-color\":\"#bf1725\",\"info-color\":\"#248dad\",\"sidebar-bg-color\":\"#344154\",\"gray\":\"#555\",\"gray-light\":\"#ccc\"}';\n  display: none; }\n\n.register-container {\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex;\n  -webkit-box-align: center;\n      -ms-flex-align: center;\n          align-items: center;\n  height: 100%;\n  width: 100%;\n  position: absolute; }\n  .register-container .card {\n    padding: 15px; }\n    .register-container .card .btn-link {\n      padding: 0; }\n      .register-container .card .btn-link:hover {\n        color: #242D3A; }\n    .register-container .card .terms {\n      font-size: 13px; }\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/pages/register/register.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return RegisterComponent; });
/* unused harmony export emailValidator */
/* unused harmony export matchingPasswords */
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



var RegisterComponent = (function () {
    function RegisterComponent(router, fb) {
        this.router = router;
        this.form = fb.group({
            name: ['', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["Validators"].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["Validators"].required, __WEBPACK_IMPORTED_MODULE_2__angular_forms__["Validators"].minLength(3)])],
            email: ['', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["Validators"].compose([__WEBPACK_IMPORTED_MODULE_2__angular_forms__["Validators"].required, emailValidator])],
            password: ['', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["Validators"].required],
            confirmPassword: ['', __WEBPACK_IMPORTED_MODULE_2__angular_forms__["Validators"].required]
        }, { validator: matchingPasswords('password', 'confirmPassword') });
        this.name = this.form.controls['name'];
        this.email = this.form.controls['email'];
        this.password = this.form.controls['password'];
        this.confirmPassword = this.form.controls['confirmPassword'];
    }
    RegisterComponent.prototype.onSubmit = function (values) {
        if (this.form.valid) {
            console.log(values);
            this.router.navigate(['/login']);
        }
    };
    RegisterComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
            selector: 'az-register',
            encapsulation: __WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewEncapsulation"].None,
            template: __webpack_require__("../../../../../src/app/pages/register/register.component.html"),
            styles: [__webpack_require__("../../../../../src/app/pages/register/register.component.scss")]
        }),
        __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["d" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["d" /* Router */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_forms__["FormBuilder"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_forms__["FormBuilder"]) === "function" && _b || Object])
    ], RegisterComponent);
    return RegisterComponent;
    var _a, _b;
}());

function emailValidator(control) {
    var emailRegexp = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
    if (control.value && !emailRegexp.test(control.value)) {
        return { invalidEmail: true };
    }
}
function matchingPasswords(passwordKey, passwordConfirmationKey) {
    return function (group) {
        var password = group.controls[passwordKey];
        var passwordConfirmation = group.controls[passwordConfirmationKey];
        if (password.value !== passwordConfirmation.value) {
            return passwordConfirmation.setErrors({ mismatchedPasswords: true });
        }
    };
}
//# sourceMappingURL=register.component.js.map

/***/ }),

/***/ "../../../../../src/app/pages/register/register.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "routes", function() { return routes; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "RegisterModule", function() { return RegisterModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common__ = __webpack_require__("../../../common/@angular/common.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_forms__ = __webpack_require__("../../../forms/@angular/forms.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__register_component__ = __webpack_require__("../../../../../src/app/pages/register/register.component.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};





var routes = [
    { path: '', component: __WEBPACK_IMPORTED_MODULE_4__register_component__["a" /* RegisterComponent */], pathMatch: 'full' }
];
var RegisterModule = (function () {
    function RegisterModule() {
    }
    RegisterModule = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["NgModule"])({
            imports: [
                __WEBPACK_IMPORTED_MODULE_1__angular_common__["CommonModule"],
                __WEBPACK_IMPORTED_MODULE_3__angular_forms__["FormsModule"],
                __WEBPACK_IMPORTED_MODULE_3__angular_forms__["ReactiveFormsModule"],
                __WEBPACK_IMPORTED_MODULE_2__angular_router__["e" /* RouterModule */].forChild(routes)
            ],
            declarations: [__WEBPACK_IMPORTED_MODULE_4__register_component__["a" /* RegisterComponent */]]
        })
    ], RegisterModule);
    return RegisterModule;
}());

//# sourceMappingURL=register.module.js.map

/***/ })

});
//# sourceMappingURL=register.module.chunk.js.map