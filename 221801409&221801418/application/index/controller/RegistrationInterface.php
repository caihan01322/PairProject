<?php
namespace app\index\controller;

use app\index\model\paper;
use think\Console;
use think\console\Table;
use think\Controller;
use think\Db;

class RegistrationInterface extends Controller
{
// 创建用户数据页面
    public function create()
    {
        return view('RegistrationInterface');
    }
    public function register(){
        if(request()->isPost()){
            $name=input('post.name');
            $account=input('post.account');
            $password=input('post.password');
            if($account!=""&&$password!=""&&$name!=""){
                $db=Db('user');
                $data=[
                    'name'=>$name,
                    'account'=>$account,
                    'password'=>$password
                ];
                //检查账号是否已经注册
                $check_username=$db
                ->where('account', $data['account'])
                ->find();
                //如果已经注册返回信息 该用户已存在
                if ($check_username) {
                    return $this->error("该用户已存在");
                }   
                if ($db->insert($data) ) {
                    $sql ="
                    CREATE TABLE IF NOT EXISTS `paper_$account`(
                        `title` longtext NOT NULL,
                        `abstract` longtext,
                        `typeandyear` varchar(255) DEFAULT NULL,
                        `keyword` longtext,
                        `releasetime` varchar(255) DEFAULT NULL,
                        `link` varchar(255) NOT NULL,
                        PRIMARY KEY (`link`)
                        ) ENGINE=MyISAM DEFAULT CHARSET=utf8";
                Db::execute($sql);
                    //注册成功返回到index模块下的index控制器下的index方法
                    return $this->success('注册成功','LoginInterface/create');
                }else{
                    //注册失败返回注册页面错误码 500
                    return $this->error('500','index');
                }
            }else{
                return $this->error("用户名、账号或密码不能为空");
            }
        }
    }
}