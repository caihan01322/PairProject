<?php

namespace app\index\controller;

use think\Controller;
use think\Db;
use think\db\connector\Mysql;

class SearchInterface extends Controller
{
    public function create()
    {
        $data  = Db::table('paper')->select();
        $haha = json_encode($data);
        $this->assign('try', $haha);
        return view('SearchInterface');
    }
    public function favoritePaper(){
        $Db = new Db;
        $value=session('account');
        $listString = $_COOKIE["favoriteList"];
        $list = json_decode($listString,true);
        $link=$list["link"];
        $result=Db::table("paper_$value")
        ->where("link",$link)
        ->find();
        if (empty($result)) {
            $data=[
                'title'=>$list["title"],
                'abstract'=>$list["abstract"],
                'typeandyear'=>$list["typeandyear"],
                'keyword'=>$list["keyword"],
                'releasetime'=>$list["releasetime"],
                'link'=>$link
            ];
            
            if (Db::table("paper_$value")->insert($data) ) {
                return $this->success('注册成功','LoginInterface/create');
            }
        }
    }
}
