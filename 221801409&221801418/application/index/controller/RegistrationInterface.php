<?php
namespace app\index\controller;

use app\index\model\paper;
use think\Controller;
class RegistrationInterface extends Controller
{
// 创建用户数据页面
    public function create()
    {
        return view('RegistrationInterface');
    }
}
