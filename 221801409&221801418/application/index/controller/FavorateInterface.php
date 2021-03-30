<?php

namespace app\index\controller;
use think\Controller;
use think\Db;
use think\db\connector\Mysql;

class FavorateInterface extends Controller
{
    public function create(){
        $Db = new Db;
        $value=session('account');
        $data  = Db::table("paper_$value")->select();
        $haha=json_encode($data);
        $this->assign('try',$haha);
        return view('FavorateInterface');
    }
    public function favoritePaper(){
        $Db = new Db;
        $value=session('account');
        $listString = $_COOKIE["deleteList"];
        $list = json_decode($listString,true);
        $link=$list["link"];
        $result=Db::table("paper")
        ->where("link",$link)
        ->find();
        if(empty($result)){
            $this->error("该用户不存在");
        }
            if($result!==false){
                return $this->success("删除成功");
            }
            else {
                return $this->error("删除失败");
            }
    }
}