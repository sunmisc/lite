.class final Lcom/vk/cameraui/CameraUIView$showCameraUI$$inlined$also$lambda$1;
.super Lkotlin/jvm/internal/Lambda;
.source "CameraUIView.kt"

# interfaces
.implements Lkotlin/jvm/b/Functions;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/vk/cameraui/CameraUIView;->Z()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lkotlin/jvm/internal/Lambda;",
        "Lkotlin/jvm/b/Functions<",
        "Lkotlin/Unit;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic $it:Lcom/vk/cameraui/widgets/masks/MasksWrap;

.field final synthetic this$0:Lcom/vk/cameraui/CameraUIView;


# direct methods
.method constructor <init>(Lcom/vk/cameraui/widgets/masks/MasksWrap;Lcom/vk/cameraui/CameraUIView;)V
    .locals 0

    iput-object p1, p0, Lcom/vk/cameraui/CameraUIView$showCameraUI$$inlined$also$lambda$1;->$it:Lcom/vk/cameraui/widgets/masks/MasksWrap;

    iput-object p2, p0, Lcom/vk/cameraui/CameraUIView$showCameraUI$$inlined$also$lambda$1;->this$0:Lcom/vk/cameraui/CameraUIView;

    const/4 p1, 0x0

    invoke-direct {p0, p1}, Lkotlin/jvm/internal/Lambda;-><init>(I)V

    return-void
.end method


# virtual methods
.method public bridge synthetic invoke()Ljava/lang/Object;
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/vk/cameraui/CameraUIView$showCameraUI$$inlined$also$lambda$1;->invoke()V

    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object v0
.end method

.method public final invoke()V
    .locals 3

    .line 2
    iget-object v0, p0, Lcom/vk/cameraui/CameraUIView$showCameraUI$$inlined$also$lambda$1;->this$0:Lcom/vk/cameraui/CameraUIView;

    invoke-static {v0}, Lcom/vk/cameraui/CameraUIView;->d(Lcom/vk/cameraui/CameraUIView;)Lcom/vk/camera/j/CadreUtils2;

    move-result-object v0

    .line 3
    iget-object v1, p0, Lcom/vk/cameraui/CameraUIView$showCameraUI$$inlined$also$lambda$1;->$it:Lcom/vk/cameraui/widgets/masks/MasksWrap;

    if-eqz v1, :cond_0

    iget-object v2, p0, Lcom/vk/cameraui/CameraUIView$showCameraUI$$inlined$also$lambda$1;->this$0:Lcom/vk/cameraui/CameraUIView;

    invoke-static {v2, v0}, Lcom/vk/cameraui/CameraUIView;->a(Lcom/vk/cameraui/CameraUIView;Lcom/vk/camera/j/CadreUtils2;)F

    move-result v0

    neg-float v0, v0

    invoke-virtual {v1, v0}, Landroid/widget/FrameLayout;->setTranslationY(F)V

    :cond_0
    return-void
.end method