<?php
namespace app\index\controller;
use think\Controller;
use think\Session;

class LoginInterface extends Controller
{
// 创建用户数据页面
    public function create()
    {
        return view('LoginInterface');//加载模板，会自动找到view下的user的user.htmurl_convertl
    }
    public function login(){
        if(request()->isPost()){
            $account=input('post.account');
            $password=input('post.password');
            if($account!=""&&$password!=""){
                $db=Db('user');
                $data=[
                    'account'=>$account,
                    'password'=>$password
                ];
                $user_info=$db
                ->where('account', $data['account'])
                ->find();
                if(empty($user_info)){
                    $this->error("该用户不存在");
                }
                else{
                    if($password!=$user_info['password']){
                        return $this->error("密码错误");
                    }
                    else{
                        Session("account",$account);
                        $this->success("登录成功",'SearchInterface/create');
                    }
                }
            }else{
                return $this->error("账号或密码不能为空");
            }
        }
    }
    public function logout(){
        session(null);
        return $this->success('退出成功','LoginInterface/create');
    }
}