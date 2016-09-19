# RxPermission
![image](https://github.com/348476129/RxPermission/blob/master/gif5.gif)

直接上图，不用自己写dialog,不用自己判断逻辑，一个代码块直接解决权限问题。
使用方法：项目的gradle文件
<pre><code>	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}</code></pre>
	
app的gradle文件
<pre><code>compile 'com.github.348476129:RxPermission:0.1.0'</code></pre>

	
代码中
<pre><code>public class MainActivity extends PermissionAppCompatActivity</code></pre>


<pre><code>checkPermission(R.string.base_permission, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.GET_ACCOUNTS,
                Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean){
                            Toast.makeText(MainActivity.this,"请求权限成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this,"请求权限成功",Toast.LENGTH_SHORT).show();
                        }
                    }
                });</code></pre>
主意：本库包含了Rxjava 和 Rxandroid 可以在依赖的时候移除。
Rxjava需要在使用后 取消订阅。
	
