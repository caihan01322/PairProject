<?php

namespace app\index\controller;
use think\Controller;
use think\Db;

class DataTest extends Controller
{
    public function create(){
        return 'index';
    }

    public function getNoModelData(){
        $data = Db::table('test')->select();
        return json($data);
    }

}