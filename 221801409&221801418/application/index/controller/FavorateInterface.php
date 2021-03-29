<?php

namespace app\index\controller;
use think\Controller;
use think\Db;
use think\db\connector\Mysql;

class FavorateInterface extends Controller
{
    public function create(){
        $Db = new Db;
        $data  = Db::table('paper')->select();
        $haha=json_encode($data);
        $this->assign('try',$haha);
        return view('FavorateInterface');
    }
}